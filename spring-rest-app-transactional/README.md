# @Transactional:
@Transactional can help us in database calls.

<li>If an exception occurs in a controller method, whatever DB calls made by the method will be reverted</li>

        @PostMapping("/api/post-bill-checkout")
        @Transactional                                                                  
        public ResponseEntity<String> billCheckout(@RequestBody OrderEN order){
            orderRepo.save(order);                                                      //#1
    
            CustomerEN customer = customerRepo.findById(order.getCustomerId()).get();   //#2
            customer.setCredit(customer.getCredit()- order.getTotalBill());
            customerRepo.save(customer);
    
    
    
    
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(order.getCustomerId())
                    .toUri();
    
            return ResponseEntity.created(location).build();
        }

### CASE 1:(method is annotated with @Transactional)

#1 : A order object is saved to ORDER_DB(customerId,totalBill)<br>
#2 : Retrieving a customer Object from DB,change the credit in CUSTOMER_DB and save the Object.

### CASE 2:(method is annotated with @Transactional)

#1 : A order object is saved to ORDER_DB(customerId,totalBill)<br>
#2 : Retrieving a customer Object from DB but **customer entry not present**, throws an Exception.
The previous transaction(#1) will be reverted.

### CASE 3: **WORST CASE** - (method is **not annotated** with @Transactional)

#1 : A order object is saved to ORDER_DB(customerId,totalBill)<br>
#2 : Retrieving a customer Object from DB but **customer entry not present**, throws an Exception.
The previous transaction(#1) will **not** be reverted.
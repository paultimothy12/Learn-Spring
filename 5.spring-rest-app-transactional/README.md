# @Transactional:
@Transactional can help us in database calls.

<li>If an exception occurs in a controller method, whatever DB calls made by the method will be reverted</li>

        @PostMapping("/api/post-bill-checkout")
        @Transactional                                                                  
        public ResponseEntity<String> billCheckout(@RequestBody OrderEN order){
            orderRepo.save(order);                                                      //#1
    
            CustomerEN customer = customerRepo.findById(order.getCustomerId()).get();   
            customer.setCredit(customer.getCredit()- order.getTotalBill());
            customerRepo.save(customer);                                                //#2
    
    
    
    
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(order.getCustomerId())
                    .toUri();
    
            return ResponseEntity.created(location).build();
        }

### CASE 1:(method is annotated with @Transactional)

#1 and #2 DB calls will be on wait till the control reaches the end of the method and then the transaction will be successful.

### CASE 2:(method is annotated with @Transactional)

#2 : Retrieving a customer Object from DB but **customer entry not present**, throws an Exception.
<br>All DB calls will be unsuccessful.
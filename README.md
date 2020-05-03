# peopleDepartment_ClaimVoucher
People and department management system And claim voucher managment system. Implemented by Spring mvc, mybatis, mysql, jsp/jstl

## Introduction
function module includes staff management module, department management module, claim voucher managment module.  
staff management module implements CRUD of staffs, self-information, change password.  
department management module implements CRUD of departments.  
claim voucher management module implements the services of processing claim vouchers. Create, Submit, Check, Pass, Update, Pay, Reject. Every operations on claim voucher will be recorded and shown in the detail page of each claim voucher.  
globle function includes Filter encoding setting, Interceptor controlled login/logout.  

## Method
* Three-layer architecture.
* mysql database 
* Dao layer: Spring-mybatis 
* Srevice layer use spring declarative transaction management
* Presentation layer: Spring MVC, spring taglib, jsp/jstl
* Sring interceptor



## Claim Voucher Processing Flow

![index page](https://github.com/KaimingCui/peopleDepartment_ClaimVoucher/blob/master/1.png)  

## some pictures

### staff and department module
![index page](https://github.com/KaimingCui/peopleDepartment_ClaimVoucher/blob/master/3.png)  
![index page](https://github.com/KaimingCui/peopleDepartment_ClaimVoucher/blob/master/4.png)  
![index page](https://github.com/KaimingCui/peopleDepartment_ClaimVoucher/blob/master/5.png) 
![index page](https://github.com/KaimingCui/peopleDepartment_ClaimVoucher/blob/master/6.png)  

### Claim voucher management
![index page](https://github.com/KaimingCui/peopleDepartment_ClaimVoucher/blob/master/7.png)  
![index page](https://github.com/KaimingCui/peopleDepartment_ClaimVoucher/blob/master/8.png)  
![index page](https://github.com/KaimingCui/peopleDepartment_ClaimVoucher/blob/master/9.png)  
![index page](https://github.com/KaimingCui/peopleDepartment_ClaimVoucher/blob/master/10.png)  
![index page](https://github.com/KaimingCui/peopleDepartment_ClaimVoucher/blob/master/11.png)  



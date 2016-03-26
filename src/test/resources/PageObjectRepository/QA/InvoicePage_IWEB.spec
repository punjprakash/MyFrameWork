Page Title: InvoicePage_IWEB

#Object Definitions
===========================================================================================================
tab_invoice        xpath     //a[text()='Invoice']
txt_invoiceMenu    xpath     //a[text()='${invoiceMenu}']
inp_invoiceValue   xpath     //span[contains(text(),'${invoice search}')]/../following-sibling::td/input
btn_search         xpath     //input[@id='ButtonSearch']
btn_memberShip     xpath    //span[text()='${membershipName}']/preceding-sibling::input
txt_priceValue     xpath     //td[contains(text(),'${productName}')]//following-sibling::td[2]
txt_quantity       xpath     //td[contains(text(),'${productName}')]//following-sibling::td[3]
txt_total          xpath     //td[contains(text(),'${productName}')]//following-sibling::td[4]
txt_discount       xpath     //td[contains(text(),'${productName}')]//following-sibling::td[5]
txt_balance        xpath     //td[contains(text(),'${productName}')]//following-sibling::td[6]
txt_invoiceValues  xpath    //span[contains(text(),'${invoice value}')]/preceding-sibling::span
btn_detailsMenu    xpath    //span[text()='${menuName}']/preceding-sibling::input
===========================================================================================================


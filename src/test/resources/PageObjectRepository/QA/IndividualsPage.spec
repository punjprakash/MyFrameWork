Page Title: IndividualsPage

#Object Definitions
===========================================================================================================
inp_fieldName               xpath    //span[contains(text(),'${fieldName}')]/../following-sibling::td/input
btn_Go                      xpath    //input[@id='ButtonSearch']
txt_userEmail                xpath    //a[text()='${email}']
txt_memberDetails           xpath    //span[@class='DataFormTextBox']
txt_additionalInfo           xpath    //span[text()='${infoValue}']
btn_memberShip              xpath    //span[text()='${membershipName}']/preceding-sibling::input
img_moreMenu               xpath    //img[@id='ProfileTabMenuImage_TS0']
link_moreMenuName           xpath    //a[@title='${menuNames}']
txt_divisionPubName        xpath    //td[contains(text(),'${divisionPubName}')]
txt_numberOfyears          xpath    //td[contains(text(),'Total Years of Service')]/following-sibling::td
txt_invoiceValue           xpath    //td[@id='UP24']
txt_priceValue              xpath     //td[contains(text(),'${productName}')]//following-sibling::td[1]
txt_quantity               xpath     //td[contains(text(),'${productName}')]//following-sibling::td[2]
txt_total                    xpath     //td[contains(text(),'${productName}')]//following-sibling::td[3]
txt_payment               xpath     //td[contains(text(),'${productName}')]//following-sibling::td[4]
txt_balance                 xpath     //td[contains(text(),'${productName}')]//following-sibling::td[5]
btn_invoiceAtMembership    xpath        //img[@id='UP26']
lnk_lastName                id        F1_HYPERLINK_4
txt_contactId               xpath     //span[contains(text(),'contact id')]/preceding-sibling::span
===========================================================================================================
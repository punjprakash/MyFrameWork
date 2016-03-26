package com.qait.keywords;

import static com.qait.automation.utils.YamlReader.getMapValue;
import static com.qait.automation.utils.YamlReader.getYamlValue;

import java.util.Map;

public class YamlInformationProvider {

	Map<String, Object> userInfoMap;

	public YamlInformationProvider() {
	}

	public YamlInformationProvider(Map<String, Object> userInfoMap) {
		this.userInfoMap = userInfoMap;
	}

	public String getEduEmpInfo(String infoType) {
		return getMapValue(this.userInfoMap, "education-and-employment" + "."
				+ infoType);
	}

	public String getEduEmpInfo(String eduEmoInfoType, String subInfo) {
		String subToken = "education-and-employment" + "." + eduEmoInfoType
				+ "." + subInfo;
		return getMapValue(this.userInfoMap, subToken);
	}

	public String getCreditCardInfo(String infoType) {
		return getYamlValue("creditCardInfo" + "." + infoType);
	}

	public String getContactInfoName(String contactInfoType) {
		return getMapValue(this.userInfoMap, "contact-information."
				+ contactInfoType);
	}

	public String getContactInfoName(String contactInfoType, String subInfo) {
		String subToken = "contact-information." + contactInfoType + "."
				+ subInfo;
		return getMapValue(this.userInfoMap, subToken);
	}

	public String getBenefitsInfo(String infoType) {
		return getMapValue(this.userInfoMap, "Benefits" + "." + infoType);

	}

	public String getASM_StoreShippingAddress(String infoType) {
		return getMapValue(this.userInfoMap, "shippingAddress" + "." + infoType);
	}

	public String getASM_StorePaymentInfo(String infoType) {
		return getMapValue(this.userInfoMap, "paymentInformation" + "."
				+ infoType);
	}

	public String getASM_OMASmokeInfo(String infoType) {
		return getMapValue(this.userInfoMap, infoType);
	}

	public String getASM_AACTContactInfo(String infoType) {
		return getMapValue(this.userInfoMap, "contactInformation" + "."
				+ infoType);
	}

	public String getASM_AACTAboutYouInfo(String infoType) {
		return getMapValue(this.userInfoMap, "aboutYou" + "." + infoType);
	}

	public String getASM_AACT_CreditCardInfo(String infoType) {
		return getMapValue(this.userInfoMap, "creditCardInfo" + "." + infoType);
	}
	
	public String getASM_email_LoginInfo(String infoType) {
		return getMapValue(this.userInfoMap, "LoginInfo" + "." + infoType);
	}
	
	public String getASM_fellowNominated_LoginInfo(String infoType) {
		return getMapValue(this.userInfoMap, "LoginInfo" + "." + infoType);
	}

	public String getReinstateMember_centralizedOrderEntry(String infoType) {
		return getMapValue(this.userInfoMap, "centralizedOrderEntry" + "." + infoType);
	}
	
	public String getMercurylogin_HomePage(String infoType) {
		return getMapValue(this.userInfoMap, "HomePage" + "." + infoType);
	}
	
	public String getMercuryflight_FlightFinder(String infoType) {
		return getMapValue(this.userInfoMap, "FlightFinder" + "." + infoType);
	}
	
	public String getMercurySelectFlight_SelectFlight(String infoType) {
		return getMapValue(this.userInfoMap, "SelectFlight" + "." + infoType);
	}
	public String getMercuryBookAFlight_BookAFlight(String infoType) {
		return getMapValue(this.userInfoMap, "BookAFlight" + "." + infoType);
	}
	public String getMercury_FlightConf(String infoType) {
		return getMapValue(this.userInfoMap, "FlightConfirmation" + "." + infoType);
	}
}

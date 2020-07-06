package task28.calculator.service;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import task28.calculator.page.EstimateWindowPage;

public class MailService {

  public String getAddress(EstimateWindowPage estimate)
      throws IOException, UnsupportedFlavorException {
    return estimate
        .jsClickEmailButton()
        .getEmaiAddress();
  }

  public String getCost(EstimateWindowPage estimate, String emailAddress) {
    return estimate
        .jsSendEmail(emailAddress)
        .openReceiveMail()
        .getCostFromEmail();
  }
}

package com.devonfw.devcon.modules.dist;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

/**
 * TODO pparrado This type ...
 *
 * @author pparrado
 */
public class CustomAuthenticator extends Authenticator {

  @Override
  protected PasswordAuthentication getPasswordAuthentication() {

    // String username = "<user>";
    // String password = "<password>";
    String username = "pparrado";
    String password = "00120Tfsrcm8344";
    return new PasswordAuthentication(username, password.toCharArray());
  }
}

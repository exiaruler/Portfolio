package uts.wsd.controller;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;

public class PayPalClient {

  /**
   *Set up the PayPal Java SDK environment with PayPal access credentials.  
   *This sample uses SandboxEnvironment. In production, use LiveEnvironment.
   */
  private PayPalEnvironment environment = new PayPalEnvironment.Sandbox(
    "AaLQ-PwHVWXEGoyu9iZ0nkcDBoLr9K41dE1tXLvJaX8r4qmWMFxuA7M62NJpM5pTE1NU_rsyW01Qe_fC",
    "EDdIP9pabD68WTNXIy3q0hmocljupZpVFsdGP9Sy05Tf2pO2KRj9RiOcFxET2P3dMZ4m7WqxD4RMKiIk	");

  /**
   *PayPal HTTP client instance with environment that has access
   *credentials context. Use to invoke PayPal APIs.
   */
  PayPalHttpClient client = new PayPalHttpClient(environment);

  /**
   *Method to get client object
   *
   *@return PayPalHttpClient client
   */
  public PayPalHttpClient client() {
    return this.client;
  }
}
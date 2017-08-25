package ibmjmstest.integration;

import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;

@Configuration
@EnableIntegration
public class IntConfiguration {

	DirectChannel catalogChannel() {
		return new DirectChannel();
	}
	
	DirectChannel productChannel() {
		return new DirectChannel();
	}
	
	DirectChannel orderItemChannel() {
		return new DirectChannel();
	}

	DirectChannel purchaseOrderChannel() {
		return new DirectChannel();
	}
}

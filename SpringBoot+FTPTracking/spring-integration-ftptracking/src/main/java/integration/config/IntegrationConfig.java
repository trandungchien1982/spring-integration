package integration.config;

import integration.module1.Service01;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.file.filters.AcceptAllFileListFilter;
import org.springframework.integration.file.filters.AcceptOnceFileListFilter;
import org.springframework.integration.sftp.inbound.SftpInboundFileSynchronizer;
import org.springframework.integration.sftp.inbound.SftpInboundFileSynchronizingMessageSource;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import java.io.File;

@Configuration
public class IntegrationConfig {

	Log log = LogFactory.getLog(getClass());

	@Bean
	protected MessageChannel sftpChannel() {
		return new DirectChannel();
	}

	@Bean
	@InboundChannelAdapter(channel = "sftpChannel", poller = @Poller(fixedDelay = "1000"))
	public MessageSource<File> sftpMessageSource(SftpInboundFileSynchronizer sftpInboundFileSynchronizer) {
		SftpInboundFileSynchronizingMessageSource source =
				new SftpInboundFileSynchronizingMessageSource(sftpInboundFileSynchronizer);
		source.setLocalDirectory(new File("Download-FTP"));
		source.setAutoCreateLocalDirectory(true);
		source.setLocalFilter(new AcceptOnceFileListFilter<>());

		// Disable Remote Download Files
		source.setMaxFetchSize(1);
		return source;
	}

	@Bean
	@ServiceActivator(inputChannel= "sftpChannel")
	public MessageHandler serviceMessageHandler1(Service01 service01) {
		return service01::handleMessage;
	}

}

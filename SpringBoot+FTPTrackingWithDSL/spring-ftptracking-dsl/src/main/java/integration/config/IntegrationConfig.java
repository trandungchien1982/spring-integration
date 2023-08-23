package integration.config;

import com.jcraft.jsch.ChannelSftp;
import integration.module1.Service01;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.filters.AcceptAllFileListFilter;
import org.springframework.integration.file.filters.AcceptOnceFileListFilter;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.integration.sftp.dsl.Sftp;
import org.springframework.integration.sftp.inbound.SftpInboundFileSynchronizer;
import org.springframework.integration.sftp.inbound.SftpInboundFileSynchronizingMessageSource;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import java.io.File;

@Configuration
public class IntegrationConfig {

	Log log = LogFactory.getLog(getClass());

	@Autowired
	SessionFactory<ChannelSftp.LsEntry> sftpSessionFactory;

	@Autowired
	Service01 service01;

	@Bean
	public IntegrationFlow sftpInboundFlow() {
		return IntegrationFlows
				.from(Sftp.inboundAdapter(this.sftpSessionFactory)
								.preserveTimestamp(true)
								.remoteDirectory("/upload")
								.regexFilter(".*\\.*$")
								.localFilenameExpression("'DOWNLOAD.' + #this.toUpperCase()")
								.localDirectory(new File("Download-FTP")),
						e -> e.id("sftpInboundAdapter")
								.autoStartup(true)
								.poller(Pollers.fixedDelay(5000)))
				.handle(service01::handleMessage)
				.get();
	}


}

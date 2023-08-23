package integration.config;

import integration.module1.Service01;
import integration.module2.Service02;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileReadingMessageSource.WatchEventType;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import java.io.File;

@Configuration
@EnableIntegration
public class IntegrationConfig {

	Log log = LogFactory.getLog(getClass());

	@Bean
	protected MessageChannel fileChannel1() {
		return new DirectChannel();
	}

	@Bean
	protected MessageChannel fileChannel2() {
		return new DirectChannel();
	}

	@Bean
	@InboundChannelAdapter(value = "fileChannel1", poller = @Poller(fixedDelay = "1000"))
	public MessageSource<File> fileReadingMessageSource1() {
		FileReadingMessageSource source = new FileReadingMessageSource();
		source.setDirectory(new File("Reports/InputFiles1"));
		source.setFilter(new SimplePatternFileListFilter("*.*"));
		source.setUseWatchService(true);
		source.setWatchEvents(WatchEventType.CREATE, WatchEventType.MODIFY, WatchEventType.DELETE);
		return source;
	}

	@Bean
	@InboundChannelAdapter(value = "fileChannel2", poller = @Poller(fixedDelay = "1000"))
	public MessageSource<File> fileReadingMessageSource2() {
		FileReadingMessageSource source = new FileReadingMessageSource();
		source.setDirectory(new File("Reports/InputFiles2"));
		source.setFilter(new SimplePatternFileListFilter("*.*"));
		source.setUseWatchService(true);
		source.setWatchEvents(WatchEventType.CREATE, WatchEventType.MODIFY, WatchEventType.DELETE);
		return source;
	}

	@Bean
	@ServiceActivator(inputChannel= "fileChannel1")
	public MessageHandler fileWritingMessageHandler1(Service01 service01) {
		return service01::handleMessage;
	}

	@Bean
	@ServiceActivator(inputChannel= "fileChannel2")
	public MessageHandler fileWritingMessageHandler2(Service02 service02) {
		return service02::handleMessage;
	}
}

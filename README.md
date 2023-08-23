# spring-integration
Các ví dụ về Spring Integration
(Mỗi branch sẽ là 1 ví dụ cụ thể)

**Tìm hiểu thêm về Spring Integration**
- https://topdev.vn/blog/gioi-thieu-ve-spring-integration/
- http://congncc.blogspot.com/2018/11/gioi-thieu-ve-spring-integration-gioi.html

========================================================================


Ví dụ về Spring Integration, xử lý SFTP File Polls trong 1 folder chỉ định.
Mỗi khi có file mới thì sẽ bắn message vào DirectChannel (1-1)
(Message đi 1 chiều dạng fire and forget 1-1)
Việc config được viết dưới dạng DSL (Domain Specific Language)
	(dạng ngôn ngữ đọc vào dễ hiểu hơn coding bình thường)


Kiểm tra ConsoleLog kết quả mỗi khi có file mới trong FTP folder: /upload
(hoặc kiểm tra trong file OutputLogs/output.log

SFTP Server:
	Host: localhost
	Port: 22
	User: foo
	Pass: pass
	
	
/upload
	=> Thông báo cho sftpChannel, gọi Service01

Folder `Download-FTP` chứa các file được tự động tải về từ SFTP Server
	(có điều chỉnh file name chuyển sang UPPERCASE và thêm prefix DOWNLOAD.

Refer to:
	https://docs.spring.io/spring-integration/reference/html/sftp.html#configuring-with-the-java-dsl
	https://viblo.asia/p/dsls-trong-kotlin-phan-1-1VgZvEy7KAw
	https://www.infoq.com/articles/internal-dsls-java/

	https://www.baeldung.com/spring-integration
	http://congncc.blogspot.com/2018/11/gioi-thieu-ve-spring-integration-gioi.html
	
	https://docs.spring.io/spring-integration/docs/5.1.0.M1/reference/html/ftp.html#ftp-inbound
	https://docs.spring.io/spring-integration/reference/html/sftp.html (SFTP)
	https://github.com/spring-projects/spring-integration-samples/tree/main/basic/sftp
	
	
	

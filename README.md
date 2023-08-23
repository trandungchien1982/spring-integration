# spring-integration
Các ví dụ về Spring Integration
(Mỗi branch sẽ là 1 ví dụ cụ thể)

**Tìm hiểu thêm về Spring Integration**
- https://topdev.vn/blog/gioi-thieu-ve-spring-integration/
- http://congncc.blogspot.com/2018/11/gioi-thieu-ve-spring-integration-gioi.html

========================================================================

Ví dụ về Spring Integration, xử lý File Polls trong 1 folder chỉ định.
Mỗi khi có file mới thì sẽ bắn message vào DirectChannel (1-1)
(Message đi 1 chiều dạng fire and forget 1-1)


Kiểm tra ConsoleLog kết quả mỗi khi có file mới trong 

	Reports/InputFiles1
	=> Thông báo cho fileChannel1, gọi Service01
	

	hoặc Reports/InputFiles2
	=> Thông báo cho fileChannel2, gọi Service02


Refer to:
	
	https://www.baeldung.com/spring-integration
	http://congncc.blogspot.com/2018/11/gioi-thieu-ve-spring-integration-gioi.html
	
	

# Lab 5
### Cho csdl như hình. Hãy tạo một ứng dụng web sử dụng spring boot rồi thực hiện các công việc sau
1. Tạo các enities sao cho khi thực thi sẽ tạo ra các bảng như hình
2. Viết các repositories interface
3. Viết các lớp services
4. Tạo các trang web cho phép công ty đăng tin tuyển người với các skill mong muốn
5. Các ứng viên khi log vào sẽ được gợi ý các công việc có skill phù hợp với mình
6. Giúp các công ty tìm các ứng viên có skill phù hợp rồi gửi mail mời.
7. Đề xuất một số skill mà ứng viên chưa có để học.
8. Và các yêu cầu khác

Đã thực hiện yêu cầu 
1. Viết các entities bằng hibernate được lưu trong modals
2. Viết các repositories interface và services để tạo ra 1000 dữ liệu address và Candidates ngẫu nhiên
3. Hiển thị được dữ liệu của Candidates lên localhost:8080/list và Candidate-Paging trên localhost:8080/candidates
4. Có thể tạo, chỉnh sửa, edit Candidates 
5. Candidate có thể log va hiển ra giao diện xem các công ty tuyển dụng (localhost:8080) Và đề xuất được công việc phù hợp với skill của mỗi Candidates
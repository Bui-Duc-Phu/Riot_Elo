# Riot_Elo



# UI APP Mobile (React Native) 

 - home: xem bảng xếp hạng rank người chơi theo điểm rank
 - User Detail:  show ra rank , điểm , name , image
 - History : show ra các trạn đấu theo dạng list mỗi item gồm thời gian thi đấu , KDA, thắng hay thua




## Công Thức Tính ELO

Hệ thống xếp hạng ELO được tính toán bằng công thức sau:

<math>
ΔELO = K × (S - E)
</math>

Trong đó:
- **ΔELO**: Số điểm tăng/giảm sau trận đấu
- **K**: Hệ số cố định (số điểm tối đa có thể tăng/giảm, ví dụ: 32)
- **S**: Kết quả thực tế
  - 1 nếu thắng
  - 0 nếu thua
- **E**: Xác suất thắng dự kiến (dự đoán trước trận đấu, dựa trên MMR)

Xác suất thắng dự kiến (E) được tính bằng:

<math>
E = \frac{1}{1 + 10^{\frac{D}{400}}}
</math>

Trong đó:
- **D**: MMR đối thủ - MMR của bạn


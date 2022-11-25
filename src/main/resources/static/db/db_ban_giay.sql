create database web_ban_giay;
use web_ban_giay;

create table chucvu(
macv int primary key auto_increment,
tencv nvarchar(50)
);

create table nhanvien(
manv int primary key auto_increment,
macv int,
ten nvarchar(50),
ngaysinh date default null,
gioitinh int,
email varchar(50),
diachi nvarchar(250),
sdt varchar(15),
matkhau varchar(50),
trangthai int default 0
);

create table khachhang(
makh int primary key auto_increment,
ten nvarchar(50),
ngaysinh date default null,
email varchar(50),
diachi nvarchar(250),
sdt varchar(15)
);

create table hoadon(
mahd int primary key auto_increment,
manv int,
makh int,
mahttt int,
ngaytao date,
ngaythanhtoan date,
ngayship date,
ngaynhan date,
tennguoinhan nvarchar(50),
diachi nvarchar(250)
);

create table hinhthucthanhtoan(
mahttt int primary key auto_increment,
ten nvarchar(50)
);

create table giohang(
magh int primary key auto_increment,
mactg int,
soluong int
);

create table loaigiay(
malg int primary key auto_increment,
ten nvarchar(50)
);

create table nsx(
mansx int primary key auto_increment,
ten nvarchar(50)
);

create table size(
mas int primary key auto_increment,
ten nvarchar(50)
);

create table chitietgiay(
mactg int primary key auto_increment,
mas int,
mansx int,
malg int,
mag int,
soluong int
);

create table giay(
mag int primary key auto_increment,
ten nvarchar(50),
gia float,
anh varchar(255),
mota nvarchar(255)
);

create table chitiethoadon(
macthd int primary key auto_increment,
mactg int,
mahd int,
soluong int,
tongtien float
);

-- TẠO QUAN HỆ GIỮA CÁC BẢNG
-- nhanvien - chucvu
alter table nhanvien add foreign key (macv) references chucvu(macv);
-- hoadon - nhanvien
alter table hoadon add foreign key (manv) references nhanvien(manv);
-- hoadon - khachhang
alter table hoadon add foreign key (makh) references khachhang(makh);
-- hoadon - hinhthucthanhtoan
alter table hoadon add foreign key (mahttt) references hinhthucthanhtoan(mahttt);
-- chitiethoadon - chitietgiay
alter table chitiethoadon add foreign key (mactg) references chitietgiay(mactg);
-- chitiethoadon - hoadon
alter table chitiethoadon add foreign key (mahd) references hoadon(mahd);
-- giohang - chitietgiay
alter table giohang add foreign key (mactg) references chitietgiay(mactg);
-- chitietgiay - size
alter table chitietgiay add foreign key (mas) references size(mas);
-- chitietgiay - nsx
alter table chitietgiay add foreign key (mansx) references nsx(mansx);
-- chitietgiay - loaigiay
alter table chitietgiay add foreign key (malg) references loaigiay(malg);
-- chitietgiay - giay
alter table chitietgiay add foreign key (mag) references giay(mag);

INSERT INTO `web_ban_giay`.`chucvu` (`tencv`) VALUES ('Admin');
INSERT INTO `web_ban_giay`.`chucvu` (`tencv`) VALUES ('Employee');
INSERT INTO `web_ban_giay`.`chucvu` (`tencv`) VALUES ('Customer');

INSERT INTO `web_ban_giay`.`nhanvien` (`macv`, `ten`, `ngaysinh`, `gioitinh`, `email`, `diachi`, `sdt`, `matkhau`, `trangthai`) VALUES ('1', 'Nguyễn Văn Tèo', '1997-10-11', '1', 'teonv@gmail.com', 'Hà Nội', '0914233578', '123456', '1');
INSERT INTO `web_ban_giay`.`nhanvien` (`macv`, `ten`, `ngaysinh`, `gioitinh`, `email`, `diachi`, `sdt`, `matkhau`, `trangthai`) VALUES ('2', 'Nguyễn Văn Bình', '2000-02-12', '1', 'binhnv@gmail.com', 'Hải Phòng', '0914228428', '123456', '1');
INSERT INTO `web_ban_giay`.`nhanvien` (`macv`, `ten`, `ngaysinh`, `gioitinh`, `email`, `diachi`, `sdt`, `matkhau`, `trangthai`) VALUES ('3', 'Phạm Thị Nở', '2002-11-02', '0', 'nopt@gmail.com', 'Thanh Hoá', '0939583578', '123456', '1');

INSERT INTO `web_ban_giay`.`hinhthucthanhtoan` (`ten`) VALUES ('Thanh toán online');
INSERT INTO `web_ban_giay`.`hinhthucthanhtoan` (`ten`) VALUES ('Thanh toán khi nhận hàng');

INSERT INTO `web_ban_giay`.`khachhang` (`ten`, `ngaysinh`, `email`, `diachi`, `sdt`) VALUES ('Nguyễn Đức Thiện', '2002-07-23', 'thiennd@gmail.com', 'Thanh Hoá', '0923173468');
INSERT INTO `web_ban_giay`.`khachhang` (`ten`, `ngaysinh`, `email`, `diachi`, `sdt`) VALUES ('Hoa Quốc Phong', '2001-12-22', 'phonghq', 'Hà Nội', '0912343777');
INSERT INTO `web_ban_giay`.`khachhang` (`ten`, `ngaysinh`, `email`, `diachi`, `sdt`) VALUES ('Lê Văn Thuỷ', '2002-07-03', 'thuylv@gmail.com', 'Thanh Hoá', '0705324123');

INSERT INTO `web_ban_giay`.`giay` (`ten`, `gia`, `mota`, `anh`) VALUES ('Giày Nike', '399000', 'Giày Nike trắng', 'https://giayxshop.vn/wp-content/uploads/2019/02/z3647648377271_1766387b92c7ca8dbc78c79a074d5e1c-300x300.jpg');
INSERT INTO `web_ban_giay`.`giay` (`ten`, `gia`, `mota`, `anh`) VALUES ('Giày MLB Ny', '599000', 'Giày MLB NY Yankees Đế Nâu Rep', 'https://giayxshop.vn/wp-content/uploads/2022/08/z3652924217168_e03755ea52f2af4a090ac8da8957ec29-300x300.jpg');
INSERT INTO `web_ban_giay`.`giay` (`ten`, `gia`, `mota`, `anh`) VALUES ('Giày Adidas Ultra', '500000', 'Adidas Ultra Boost Ghi Trắng REP', 'https://giayxshop.vn/wp-content/uploads/2022/07/z3652966025314_1b35eea59172e7d271bab82193db16ef-300x300.jpg');
INSERT INTO `web_ban_giay`.`giay` (`ten`, `gia`, `mota`, `anh`) VALUES ('Giày Ni.ke Jordan 1', '349000', 'Ni.ke Jordan 1 High Xanh Viền Bạc REP', 'https://giayxshop.vn/wp-content/uploads/2022/01/z3660355260020_444ed909dde9081551103b4a96948f0e-300x300.jpg');

INSERT INTO `web_ban_giay`.`loaigiay` (`ten`) VALUES ('Giày Nike');
INSERT INTO `web_ban_giay`.`loaigiay` (`ten`) VALUES ('Giày Adidas');
INSERT INTO `web_ban_giay`.`loaigiay` (`ten`) VALUES ('Giày thể thao');
INSERT INTO `web_ban_giay`.`loaigiay` (`ten`) VALUES ('Giày da');
INSERT INTO `web_ban_giay`.`loaigiay` (`ten`) VALUES ('Giày cao gót');

INSERT INTO `web_ban_giay`.`size` (`ten`) VALUES ('36');
INSERT INTO `web_ban_giay`.`size` (`ten`) VALUES ('37');
INSERT INTO `web_ban_giay`.`size` (`ten`) VALUES ('38');
INSERT INTO `web_ban_giay`.`size` (`ten`) VALUES ('39');
INSERT INTO `web_ban_giay`.`size` (`ten`) VALUES ('40');
INSERT INTO `web_ban_giay`.`size` (`ten`) VALUES ('41');
INSERT INTO `web_ban_giay`.`size` (`ten`) VALUES ('42');
INSERT INTO `web_ban_giay`.`size` (`ten`) VALUES ('43');
INSERT INTO `web_ban_giay`.`size` (`ten`) VALUES ('44');
INSERT INTO `web_ban_giay`.`size` (`ten`) VALUES ('45');

INSERT INTO `web_ban_giay`.`nsx` (`ten`) VALUES ('Adidas');
INSERT INTO `web_ban_giay`.`nsx` (`ten`) VALUES ('Nike');
INSERT INTO `web_ban_giay`.`nsx` (`ten`) VALUES ('Gucci');

INSERT INTO `web_ban_giay`.`chitietgiay` (`mas`, `mansx`, `malg`, `mag`, `soluong`) VALUES ('1', '1', '1', '1', '20');
INSERT INTO `web_ban_giay`.`chitietgiay` (`mas`, `mansx`, `malg`, `mag`, `soluong`) VALUES ('2', '2', '2', '2', '20');
INSERT INTO `web_ban_giay`.`chitietgiay` (`mas`, `mansx`, `malg`, `mag`, `soluong`) VALUES ('4', '3', '3', '3', '20');
INSERT INTO `web_ban_giay`.`chitietgiay` (`mas`, `mansx`, `malg`, `mag`, `soluong`) VALUES ('5', '2', '1', '4', '20');
INSERT INTO `web_ban_giay`.`chitietgiay` (`mas`, `mansx`, `malg`, `mag`, `soluong`) VALUES ('6', '1', '1', '2', '20');

INSERT INTO `web_ban_giay`.`giohang` (`mactg`, `soluong`) VALUES ('1', '2');
INSERT INTO `web_ban_giay`.`giohang` (`mactg`, `soluong`) VALUES ('2', '1');
INSERT INTO `web_ban_giay`.`giohang` (`mactg`, `soluong`) VALUES ('3', '3');
INSERT INTO `web_ban_giay`.`giohang` (`mactg`, `soluong`) VALUES ('4', '1');
INSERT INTO `web_ban_giay`.`giohang` (`mactg`, `soluong`) VALUES ('5', '1');

INSERT INTO `web_ban_giay`.`hoadon` (`manv`, `makh`, `mahttt`, `ngaytao`, `ngaythanhtoan`, `ngayship`, `ngaynhan`, `tennguoinhan`, `diachi`) VALUES ('1', '1', '1', '2022-10-19', '2022-10-19', '2022-10-20', '2022-10-25', 'Thiện', 'Hà Nội');
INSERT INTO `web_ban_giay`.`hoadon` (`manv`, `makh`, `mahttt`, `ngaytao`, `ngayship`, `ngaynhan`, `tennguoinhan`, `diachi`) VALUES ('2', '2', '2', '2022-10-20', '2022-10-20', '2022-10-25', 'Phong', 'Hà Nội');
INSERT INTO `web_ban_giay`.`hoadon` (`manv`, `makh`, `mahttt`, `ngaytao`, `ngaythanhtoan`, `ngayship`, `ngaynhan`, `tennguoinhan`, `diachi`) VALUES ('3', '3', '1', '2022-10-20', '2022-10-20', '2022-10-20', '2022-10-25', 'Thuỷ', 'Hà Nội');
INSERT INTO `web_ban_giay`.`hoadon` (`manv`, `makh`, `mahttt`, `ngaytao`, `ngaythanhtoan`, `ngayship`, `ngaynhan`, `tennguoinhan`, `diachi`) VALUES ('2', '2', '1', '2022-10-20', '2022-10-20', '2022-10-20', '2022-10-25', 'Phong', 'Hà Nội');
INSERT INTO `web_ban_giay`.`hoadon` (`manv`, `makh`, `mahttt`, `ngaytao`, `ngayship`, `ngaynhan`, `tennguoinhan`, `diachi`) VALUES ('1', '1', '2', '2022-10-20', '2022-10-20', '2022-10-25', 'Thiện', 'Hà Nội');

INSERT INTO `web_ban_giay`.`chitiethoadon` (`mactg`, `mahd`, `soluong`, `tongtien`) VALUES ('1', '1', '1', '399000');
INSERT INTO `web_ban_giay`.`chitiethoadon` (`mactg`, `mahd`, `soluong`, `tongtien`) VALUES ('2', '2', '1', '599000');
INSERT INTO `web_ban_giay`.`chitiethoadon` (`mactg`, `mahd`, `soluong`, `tongtien`) VALUES ('3', '3', '1', '500000');
INSERT INTO `web_ban_giay`.`chitiethoadon` (`mactg`, `mahd`, `soluong`, `tongtien`) VALUES ('4', '4', '1', '349000');
INSERT INTO `web_ban_giay`.`chitiethoadon` (`mactg`, `mahd`, `soluong`, `tongtien`) VALUES ('5', '5', '1', '599000');

create database FashionShop
go
use FashionShop
go

create table NhanVien
(
MaNV nvarchar(20) primary key not null,
TenNV nvarchar(50) ,
MatKhau nvarchar(50),
VaiTro bit,
Hinh nvarchar(50) null
)
create table KhachHang
(
MaKH nvarchar(20) primary key not null,
TenKH nvarchar(50) null,
GioiTinh bit null,
SDT varchar(10) null,
DiaChi nvarchar(Max) null,
GhiChu nvarchar(Max) null,
NgayTao date 
)
create table LoaiSanPham
(
MaLSP int identity(1,1) primary key not null,
TenLSP nvarchar(50) null
)
create table DonViTinh
(
MaDVT int identity(1,1) primary key not null,
TenDVT nvarchar(50) null
)
create table SanPham
(
MaSP nvarchar(20) primary key not null,
TenSP nvarchar(50),
MaDVT int,
MaLSP int ,
GiaBan float not null,
NgayTao date,
Hinh nvarchar(50) default (null),
MaNV nvarchar(20),
foreign key (MaNV) references NhanVien(MaNV),
foreign key (MaDVT) references DonViTinh(MaDVT),
foreign key (MaLSP) references LoaiSanPham(MaLSP)
)
create table Kho
(
MaNK int identity(1,1) primary key not null,
MaSP nvarchar(20),
MaDVT int,
SLNhap int,
GiaNhap float,
TongGia float,
NgayTao date,
foreign key (MaSP) references SanPham(MaSP),
foreign key (MaDVT) references DonViTinh(MaDVT)
)

create table HoaDon 
(	
MaHD nvarchar(20) primary key not null,
MaKH nvarchar(20),
NgayTao date,
TienDV float,
GiamGia float,
TongTien float,
TienKhachTra float,
TienTraKhach float,
MaNV nvarchar(20),
foreign key (MaKH) references KhachHang(MaKH),
foreign key (MaNV) references NhanVien(MaNV)
)
create table HoaDonChiTiet
(
MaHDCT int identity(1,1)primary key not null,
MaHD nvarchar(20),
MaSP nvarchar(20),
DonGia float null,
SoLuong int null,
ThanhTien float null,
foreign key (MaHD) references HoaDon(MaHD),
foreign key (MaSP) references SanPham(MaSP)
)
---------------------------thủ tục--------------------------------
use FashionShop
go
create proc tk_HoaDon(@Mon int ,@Year int)
as begin 
	select 
		HoaDon.MaHD,
		Count(HoaDonChiTiet.MaSP) as tongSP,
		sum(HoaDon.GiamGia) giamgia,
		HoaDon.NgayTao as ngaytao,
		NhanVien.TenNV as tennv
	from HoaDon,HoaDonChiTiet,NhanVien
	where HoaDon.MaHD = HoaDonChiTiet.MaHD and HoaDon.MaNV = NhanVien.MaNV and MONTH(HoaDon.NgayTao) = @Mon and year(HoaDon.NgayTao) = @Year
	group by HoaDon.MaHD,HoaDon.NgayTao,NhanVien.TenNV
end
go
--
create proc tk_SPBan(@Mon int ,@Year int)
as begin 
		select 
		SanPham.TenSP ,
		DonViTinh.TenDVT,
		sum(HoaDonChiTiet.SoLuong) soluong,
		HoaDonChiTiet.DonGia,
		Sum(soluong * HoaDonChiTiet.DonGia) thanhtien,
		kho.GiaNhap as gianhap,
		sum(soluong * Kho.GiaNhap) tongianhap
	from SanPham,DonViTinh,HoaDonChiTiet,Kho,HoaDon
	where DonViTinh.MaDVT = SanPham.MaDVT and HoaDonChiTiet.MaSP = SanPham.MaSP and Kho.MaSP = HoaDonChiTiet.MaSP and HoaDon.MaHD = HoaDonChiTiet.MaHD and MONTH(HoaDon.NgayTao) = @Mon and year(HoaDon.NgayTao) = @Year
	group by SanPham.TenSP,DonViTinh.TenDVT,HoaDonChiTiet.DonGia,kho.GiaNhap
end
go
-----------------------
create proc tk_HoaDon1(@Day int ,@Mon int ,@Year int)
as begin 
	select 
		HoaDon.MaHD,
		Count(HoaDonChiTiet.MaSP) as tongSP,
		sum(HoaDon.GiamGia) giamgia,
		HoaDon.NgayTao as ngaytao,
		NhanVien.TenNV as tennv
	from HoaDon,HoaDonChiTiet,NhanVien
	where HoaDon.MaHD = HoaDonChiTiet.MaHD and HoaDon.MaNV = NhanVien.MaNV and DAY(HoaDon.NgayTao) = @Day and MONTH(HoaDon.NgayTao) = @Mon and year(HoaDon.NgayTao) = @Year
	group by HoaDon.MaHD,HoaDon.NgayTao,NhanVien.TenNV
end
go
--
create proc tk_SPBan1(@Day int, @Mon int ,@Year int)
as begin 
		select 
		SanPham.TenSP ,
		DonViTinh.TenDVT,
		sum(HoaDonChiTiet.SoLuong) soluong,
		HoaDonChiTiet.DonGia,
		Sum(soluong * HoaDonChiTiet.DonGia) thanhtien,
		kho.GiaNhap as gianhap,
		sum(soluong * Kho.GiaNhap) tongianhap
	from SanPham,DonViTinh,HoaDonChiTiet,Kho,HoaDon
	where DonViTinh.MaDVT = SanPham.MaDVT and HoaDonChiTiet.MaSP = SanPham.MaSP and Kho.MaSP = HoaDonChiTiet.MaSP and HoaDon.MaHD = HoaDonChiTiet.MaHD and DAY(HoaDon.NgayTao) = @Day and MONTH(HoaDon.NgayTao) = @Mon and year(HoaDon.NgayTao) = @Year
	group by SanPham.TenSP,DonViTinh.TenDVT,HoaDonChiTiet.DonGia,kho.GiaNhap
end
go
-----------------------
create proc tk_ToanHoaDon
as begin 
		select 
		HoaDon.MaHD,
		Count(HoaDonChiTiet.MaSP) as tongSP,
		sum(HoaDon.GiamGia) giamgia,
		HoaDon.NgayTao as ngaytao,
		NhanVien.TenNV as tennv
	from HoaDon,HoaDonChiTiet,NhanVien
	where HoaDon.MaHD = HoaDonChiTiet.MaHD and HoaDon.MaNV = NhanVien.MaNV
	group by HoaDon.MaHD,HoaDon.NgayTao,NhanVien.TenNV
end
go
create proc tk_ToanSanPham
as begin 
		select 
		SanPham.TenSP ,
		DonViTinh.TenDVT,
		sum(HoaDonChiTiet.SoLuong) soluong,
		HoaDonChiTiet.DonGia,
		Sum(soluong * HoaDonChiTiet.DonGia) thanhtien,
		kho.GiaNhap as gianhap,
		sum(soluong * Kho.GiaNhap) tongianhap
	from SanPham,DonViTinh,HoaDonChiTiet,Kho,HoaDon
	where DonViTinh.MaDVT = SanPham.MaDVT and HoaDonChiTiet.MaSP = SanPham.MaSP and Kho.MaSP = HoaDonChiTiet.MaSP and HoaDon.MaHD = HoaDonChiTiet.MaHD 
	group by SanPham.TenSP,DonViTinh.TenDVT,HoaDonChiTiet.DonGia,kho.GiaNhap
end
go
----------------------------------------
USE [FashionShop]
GO
INSERT [dbo].[NhanVien] ([MaNV], [TenNV], [MatKhau], [VaiTro], [Hinh]) VALUES (N'Admin', N'Vo Van Thang', N'123', 1, NULL)
GO
SET IDENTITY_INSERT [dbo].[LoaiSanPham] ON 

INSERT [dbo].[LoaiSanPham] ([MaLSP], [TenLSP]) VALUES (1, N'Quần áo')
INSERT [dbo].[LoaiSanPham] ([MaLSP], [TenLSP]) VALUES (2, N'Mĩ phẩm')
INSERT [dbo].[LoaiSanPham] ([MaLSP], [TenLSP]) VALUES (3, N'Nước Hoa')
INSERT [dbo].[LoaiSanPham] ([MaLSP], [TenLSP]) VALUES (4, N'Giày dép')
SET IDENTITY_INSERT [dbo].[LoaiSanPham] OFF
GO
SET IDENTITY_INSERT [dbo].[DonViTinh] ON 

INSERT [dbo].[DonViTinh] ([MaDVT], [TenDVT]) VALUES (1, N'Cái')
INSERT [dbo].[DonViTinh] ([MaDVT], [TenDVT]) VALUES (2, N'Hộp')
INSERT [dbo].[DonViTinh] ([MaDVT], [TenDVT]) VALUES (3, N'Chai')
INSERT [dbo].[DonViTinh] ([MaDVT], [TenDVT]) VALUES (4, N'Bộ')
SET IDENTITY_INSERT [dbo].[DonViTinh] OFF
GO
INSERT [dbo].[SanPham] ([MaSP], [TenSP], [MaDVT], [MaLSP], [GiaBan], [NgayTao], [Hinh], [MaNV]) VALUES (N'AT220', N'Áo thun', 1, 1, 220000, CAST(N'2020-11-26' AS Date), NULL, N'Admin')
INSERT [dbo].[SanPham] ([MaSP], [TenSP], [MaDVT], [MaLSP], [GiaBan], [NgayTao], [Hinh], [MaNV]) VALUES (N'GYZ1K', N'Giày Ezy', 2, 4, 1000000, CAST(N'2020-11-26' AS Date), NULL, N'Admin')
INSERT [dbo].[SanPham] ([MaSP], [TenSP], [MaDVT], [MaLSP], [GiaBan], [NgayTao], [Hinh], [MaNV]) VALUES (N'NHH550', N'Nước hoa hồng', 3, 3, 500000, CAST(N'2020-11-26' AS Date), NULL, N'Admin')
INSERT [dbo].[SanPham] ([MaSP], [TenSP], [MaDVT], [MaLSP], [GiaBan], [NgayTao], [Hinh], [MaNV]) VALUES (N'QK300', N'Quần Kaki', 1, 1, 300000, CAST(N'2020-11-26' AS Date), NULL, N'Admin')
GO
INSERT [dbo].[KhachHang] ([MaKH], [TenKH], [GioiTinh], [SDT], [DiaChi], [GhiChu], [NgayTao]) VALUES (N'KH01', N'Cao Công Hòa', 1, N'0956425876', N'Gia lai', N'', CAST(N'2020-11-26' AS Date))
INSERT [dbo].[KhachHang] ([MaKH], [TenKH], [GioiTinh], [SDT], [DiaChi], [GhiChu], [NgayTao]) VALUES (N'KH02', N'Trương Công Mạnh Đình', 1, N'0164853495', N'Hà Nội', N'', CAST(N'2020-11-26' AS Date))
INSERT [dbo].[KhachHang] ([MaKH], [TenKH], [GioiTinh], [SDT], [DiaChi], [GhiChu], [NgayTao]) VALUES (N'KH03', N'Nguyễn Đức Hoàng Sang', 1, N'0345954231', N'Hồ Chí Minh', N'', CAST(N'2020-11-26' AS Date))
GO
SET IDENTITY_INSERT [dbo].[Kho] ON 

INSERT [dbo].[Kho] ([MaNK], [MaSP], [MaDVT], [SLNhap], [GiaNhap], [TongGia], [NgayTao]) VALUES (1, N'AT220', 1, 50, 150000, 0, CAST(N'2020-11-26' AS Date))
INSERT [dbo].[Kho] ([MaNK], [MaSP], [MaDVT], [SLNhap], [GiaNhap], [TongGia], [NgayTao]) VALUES (2, N'GYZ1K', 2, 20, 800000, 0, CAST(N'2020-11-26' AS Date))
INSERT [dbo].[Kho] ([MaNK], [MaSP], [MaDVT], [SLNhap], [GiaNhap], [TongGia], [NgayTao]) VALUES (3, N'NHH550', 3, 10, 450000, 0, CAST(N'2020-11-26' AS Date))
INSERT [dbo].[Kho] ([MaNK], [MaSP], [MaDVT], [SLNhap], [GiaNhap], [TongGia], [NgayTao]) VALUES (4, N'QK300', 1, 50, 250000, 0, CAST(N'2020-11-26' AS Date))
SET IDENTITY_INSERT [dbo].[Kho] OFF
GO

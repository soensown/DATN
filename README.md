
USE CommercialWebsite
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[brands](
	[brand_id] [int] IDENTITY(1,1) NOT NULL,
	[brand_name] [nvarchar](255) NULL,
	[logo] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[brand_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[cart_items]    Script Date: 7/4/2025 11:48:31 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[cart_items](
	[cart_id] [varchar](50) NOT NULL,
	[user_id] [varchar](50) NULL,
	[product_detail_id] [varchar](50) NULL,
	[quantity] [int] NULL,
	[created_date] [datetime] NULL,
	[created_by] [varchar](50) NULL,
	[updated_date] [datetime] NULL,
	[updated_by] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[cart_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[categories]    Script Date: 7/4/2025 11:48:31 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[categories](
	[category_id] [varchar](50) NOT NULL,
	[category_name] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[category_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[colors]    Script Date: 7/4/2025 11:48:31 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[colors](
	[color_id] [int] IDENTITY(1,1) NOT NULL,
	[color_name] [nvarchar](255) NULL,
	[color_code] [varchar](15) NULL,
PRIMARY KEY CLUSTERED 
(
	[color_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[common_codes]    Script Date: 7/4/2025 11:48:31 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[common_codes](
	[comm_cd] [nvarchar](255) NOT NULL,
	[comm_nm] [nvarchar](255) NULL,
	[description] [nvarchar](255) NULL,
	[lev] [int] NULL,
	[up_comm_cd] [nvarchar](255) NULL,
	[use_yn] [nvarchar](255) NULL,
	[value_config] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[comm_cd] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[discounts]    Script Date: 7/4/2025 11:48:31 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[discounts](
	[discount_id] [varchar](50) NOT NULL,
	[discount_value] [int] NULL,
	[discount_type] [nvarchar](255) NULL,
	[start_date] [datetime] NULL,
	[end_date] [datetime] NULL,
	[status] [nvarchar](255) NULL,
	[description] [nvarchar](255) NULL,
	[condition] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[discount_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[material]    Script Date: 7/4/2025 11:48:31 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[material](
	[material_id] [int] IDENTITY(1,1) NOT NULL,
	[material_name] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[material_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[menu_role]    Script Date: 7/4/2025 11:48:31 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[menu_role](
	[menu_role_id] [int] IDENTITY(1,1) NOT NULL,
	[role_id] [varchar](50) NULL,
	[menu_id] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[menu_role_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[order_items]    Script Date: 7/4/2025 11:48:31 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[order_items](
	[order_item_id] [bigint] IDENTITY(1,1) NOT NULL,
	[order_id] [varchar](50) NULL,
	[product_detail_id] [varchar](50) NULL,
	[quantity] [int] NULL,
	[unit_price] [decimal](10, 2) NULL,
	[discount_price] [decimal](10, 2) NULL,
	[total_price] [decimal](10, 2) NULL,
PRIMARY KEY CLUSTERED 
(
	[order_item_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[orders]    Script Date: 7/4/2025 11:48:31 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[orders](
	[order_id] [varchar](50) NOT NULL,
	[user_id] [varchar](50) NULL,
	[discount_id] [varchar](50) NULL,
	[total_price] [decimal](10, 2) NULL,
	[status] [nvarchar](255) NULL,
	[created_date] [datetime] NULL,
	[created_by] [varchar](50) NULL,
	[updated_date] [datetime] NULL,
	[updated_by] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[order_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[product_details]    Script Date: 7/4/2025 11:48:31 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[product_details](
	[product_detail_id] [varchar](50) NOT NULL,
	[product_id] [varchar](50) NULL,
	[color_id] [int] NULL,
	[size_id] [int] NULL,
	[quantity] [int] NULL,
	[description] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[product_detail_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[product_images]    Script Date: 7/4/2025 11:48:31 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[product_images](
	[image_id] [varchar](50) NOT NULL,
	[product_detail_id] [varchar](50) NULL,
	[image_url] [nvarchar](255) NULL,
	[is_thumbnail] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[image_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[product_materials]    Script Date: 7/4/2025 11:48:31 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[product_materials](
	[product_id] [varchar](50) NOT NULL,
	[material_id] [int] NOT NULL,
	[percentage] [decimal](5, 2) NULL,
PRIMARY KEY CLUSTERED 
(
	[product_id] ASC,
	[material_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[products]    Script Date: 7/4/2025 11:48:31 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[products](
	[product_id] [varchar](50) NOT NULL,
	[product_name] [nvarchar](255) NULL,
	[description] [nvarchar](255) NULL,
	[category_id] [varchar](50) NULL,
	[discount_price] [bigint] NULL,
	[unit_price] [bigint] NULL,
	[is_discount] [bit] NULL,
	[is_special] [bit] NULL,
	[brand_id] [int] NULL,
	[weight] [decimal](10, 2) NULL,
	[created_date] [datetime] NULL,
	[created_by] [varchar](50) NULL,
	[updated_date] [datetime] NULL,
	[updated_by] [varchar](50) NULL,
	[thumbnail] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[product_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[roles]    Script Date: 7/4/2025 11:48:31 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[roles](
	[role_id] [varchar](50) NOT NULL,
	[role_name] [nvarchar](255) NULL,
	[role_code] [nvarchar](255) NULL,
	[description] [nvarchar](255) NULL,
	[use_yn] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[role_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[shipments]    Script Date: 7/4/2025 11:48:31 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[shipments](
	[shipment_id] [int] IDENTITY(1,1) NOT NULL,
	[order_id] [varchar](50) NOT NULL,
	[user_id] [varchar](50) NOT NULL,
	[shipping_address] [nvarchar](max) NULL,
	[shipping_method] [nvarchar](100) NULL,
	[tracking_number] [nvarchar](100) NULL,
	[shipment_status] [nvarchar](50) NULL,
	[shipped_date] [datetime] NULL,
	[delivered_date] [datetime] NULL,
	[created_at] [datetime] NULL,
	[updated_at] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[shipment_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[shop_menus]    Script Date: 7/4/2025 11:48:31 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[shop_menus](
	[menu_id] [varchar](50) NOT NULL,
	[upper_menu_id] [varchar](50) NULL,
	[menu_name] [nvarchar](255) NULL,
	[link_uri] [nvarchar](255) NULL,
	[display_order] [int] NULL,
	[use_yn] [nvarchar](255) NULL,
	[lev] [int] NULL,
	[description] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[menu_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[sizes]    Script Date: 7/4/2025 11:48:31 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[sizes](
	[size_id] [int] IDENTITY(1,1) NOT NULL,
	[size_name] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[size_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[users]    Script Date: 7/4/2025 11:48:31 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[users](
	[user_id] [varchar](50) NOT NULL,
	[username] [nvarchar](255) NULL,
	[password] [nvarchar](255) NULL,
	[full_name] [nvarchar](255) NULL,
	[email] [nvarchar](255) NULL,
	[phone_number] [nvarchar](255) NULL,
	[address] [nvarchar](255) NULL,
	[status] [nvarchar](255) NULL,
	[is_del] [bit] NULL,
	[created_date] [datetime] NULL,
	[created_by] [varchar](50) NULL,
	[updated_date] [datetime] NULL,
	[updated_by] [varchar](50) NULL,
	[role_id] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[user_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[shipments] ADD  DEFAULT (getdate()) FOR [created_at]
GO
ALTER TABLE [dbo].[shipments] ADD  DEFAULT (getdate()) FOR [updated_at]
GO
ALTER TABLE [dbo].[cart_items]  WITH CHECK ADD FOREIGN KEY([product_detail_id])
REFERENCES [dbo].[product_details] ([product_detail_id])
GO
ALTER TABLE [dbo].[cart_items]  WITH CHECK ADD FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([user_id])
GO
ALTER TABLE [dbo].[menu_role]  WITH CHECK ADD FOREIGN KEY([menu_id])
REFERENCES [dbo].[shop_menus] ([menu_id])
GO
ALTER TABLE [dbo].[menu_role]  WITH CHECK ADD FOREIGN KEY([role_id])
REFERENCES [dbo].[roles] ([role_id])
GO
ALTER TABLE [dbo].[order_items]  WITH CHECK ADD FOREIGN KEY([order_id])
REFERENCES [dbo].[orders] ([order_id])
GO
ALTER TABLE [dbo].[order_items]  WITH CHECK ADD FOREIGN KEY([product_detail_id])
REFERENCES [dbo].[product_details] ([product_detail_id])
GO
ALTER TABLE [dbo].[orders]  WITH CHECK ADD FOREIGN KEY([discount_id])
REFERENCES [dbo].[discounts] ([discount_id])
GO
ALTER TABLE [dbo].[orders]  WITH CHECK ADD FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([user_id])
GO
ALTER TABLE [dbo].[product_details]  WITH CHECK ADD FOREIGN KEY([color_id])
REFERENCES [dbo].[colors] ([color_id])
GO
ALTER TABLE [dbo].[product_details]  WITH CHECK ADD FOREIGN KEY([product_id])
REFERENCES [dbo].[products] ([product_id])
GO
ALTER TABLE [dbo].[product_details]  WITH CHECK ADD FOREIGN KEY([size_id])
REFERENCES [dbo].[sizes] ([size_id])
GO
ALTER TABLE [dbo].[product_images]  WITH CHECK ADD FOREIGN KEY([product_detail_id])
REFERENCES [dbo].[product_details] ([product_detail_id])
GO
ALTER TABLE [dbo].[product_materials]  WITH CHECK ADD FOREIGN KEY([material_id])
REFERENCES [dbo].[material] ([material_id])
GO
ALTER TABLE [dbo].[product_materials]  WITH CHECK ADD FOREIGN KEY([product_id])
REFERENCES [dbo].[products] ([product_id])
GO
ALTER TABLE [dbo].[products]  WITH CHECK ADD FOREIGN KEY([brand_id])
REFERENCES [dbo].[brands] ([brand_id])
GO
ALTER TABLE [dbo].[products]  WITH CHECK ADD FOREIGN KEY([category_id])
REFERENCES [dbo].[categories] ([category_id])
GO
ALTER TABLE [dbo].[products]  WITH CHECK ADD FOREIGN KEY([created_by])
REFERENCES [dbo].[users] ([user_id])
GO
ALTER TABLE [dbo].[products]  WITH CHECK ADD FOREIGN KEY([updated_by])
REFERENCES [dbo].[users] ([user_id])
GO
ALTER TABLE [dbo].[shipments]  WITH CHECK ADD  CONSTRAINT [FK_shipments_order] FOREIGN KEY([order_id])
REFERENCES [dbo].[orders] ([order_id])
GO
ALTER TABLE [dbo].[shipments] CHECK CONSTRAINT [FK_shipments_order]
GO
ALTER TABLE [dbo].[shipments]  WITH CHECK ADD  CONSTRAINT [FK_shipments_user] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([user_id])
GO
ALTER TABLE [dbo].[shipments] CHECK CONSTRAINT [FK_shipments_user]
GO
ALTER TABLE [dbo].[users]  WITH CHECK ADD FOREIGN KEY([role_id])
REFERENCES [dbo].[roles] ([role_id])
GO
ALTER TABLE [dbo].[product_materials]  WITH CHECK ADD CHECK  (([percentage]>=(0) AND [percentage]<=(100)))
GO
USE CommercialWebsite
GO

-- ============================================
-- 10 INSERT cho moi bang
-- Thu tu INSERT da duoc sap xep theo FOREIGN KEY
-- ============================================

-- 1. roles
INSERT INTO roles (role_id, role_name, role_code, description, use_yn) VALUES
('ROLE001', N'Quản trị viên', N'ADMIN', N'Quản trị hệ thống', N'Y'),
('ROLE002', N'Khách hàng', N'CUSTOMER', N'Khách hàng mua hàng', N'Y'),
('ROLE003', N'Nhân viên', N'STAFF', N'Nhân viên cửa hàng', N'Y'),
('ROLE004', N'Quản lý', N'MANAGER', N'Quản lý cửa hàng', N'Y'),
('ROLE005', N'Kho', N'WAREHOUSE', N'Nhân viên kho', N'Y'),
('ROLE006', N'Kế toán', N'ACCOUNTANT', N'Nhân viên kế toán', N'Y'),
('ROLE007', N'Bán hàng', N'SALES', N'Nhân viên bán hàng', N'Y'),
('ROLE008', N'Marketing', N'MARKETING', N'Nhân viên marketing', N'Y'),
('ROLE009', N'Chăm sóc khách hàng', N'CSKH', N'Hỗ trợ khách hàng', N'Y'),
('ROLE010', N'Kiểm duyệt', N'MODERATOR', N'Kiểm duyệt nội dung', N'Y');
GO

-- 2. users
INSERT INTO users
(user_id, username, password, full_name, email, phone_number, address, status, is_del, created_date, created_by, updated_date, updated_by, role_id)
VALUES
('USR001', N'admin', N'123456', N'Nguyễn Văn Admin', N'admin@havenshop.vn', N'0900000001', N'Hà Nội', N'ACTIVE', 0, GETDATE(), NULL, GETDATE(), NULL, 'ROLE001'),
('USR002', N'khachhang01', N'123456', N'Nguyễn Văn An', N'an@gmail.com', N'0900000002', N'Bắc Ninh', N'ACTIVE', 0, GETDATE(), 'USR001', GETDATE(), 'USR001', 'ROLE002'),
('USR003', N'khachhang02', N'123456', N'Trần Thị Bình', N'binh@gmail.com', N'0900000003', N'Hà Nội', N'ACTIVE', 0, GETDATE(), 'USR001', GETDATE(), 'USR001', 'ROLE002'),
('USR004', N'nhanvien01', N'123456', N'Lê Văn Cường', N'cuong@gmail.com', N'0900000004', N'Hà Nội', N'ACTIVE', 0, GETDATE(), 'USR001', GETDATE(), 'USR001', 'ROLE003'),
('USR005', N'quanly01', N'123456', N'Phạm Văn Dũng', N'dung@gmail.com', N'0900000005', N'Hải Phòng', N'ACTIVE', 0, GETDATE(), 'USR001', GETDATE(), 'USR001', 'ROLE004'),
('USR006', N'kho01', N'123456', N'Hoàng Văn Em', N'em@gmail.com', N'0900000006', N'Hà Nội', N'ACTIVE', 0, GETDATE(), 'USR001', GETDATE(), 'USR001', 'ROLE005'),
('USR007', N'ketoan01', N'123456', N'Vũ Thị Hoa', N'hoa@gmail.com', N'0900000007', N'Hà Nội', N'ACTIVE', 0, GETDATE(), 'USR001', GETDATE(), 'USR001', 'ROLE006'),
('USR008', N'banhang01', N'123456', N'Đỗ Văn Long', N'long@gmail.com', N'0900000008', N'Nam Định', N'ACTIVE', 0, GETDATE(), 'USR001', GETDATE(), 'USR001', 'ROLE007'),
('USR009', N'marketing01', N'123456', N'Bùi Thị Mai', N'mai@gmail.com', N'0900000009', N'Hà Nội', N'ACTIVE', 0, GETDATE(), 'USR001', GETDATE(), 'USR001', 'ROLE008'),
('USR010', N'cskh01', N'123456', N'Ngô Thị Lan', N'lan@gmail.com', N'0900000010', N'Hưng Yên', N'ACTIVE', 0, GETDATE(), 'USR001', GETDATE(), 'USR001', 'ROLE009');
GO

-- 3. categories
INSERT INTO categories (category_id, category_name) VALUES
('CAT001', N'Áo thun'),
('CAT002', N'Áo sơ mi'),
('CAT003', N'Quần jean'),
('CAT004', N'Quần kaki'),
('CAT005', N'Áo khoác'),
('CAT006', N'Váy'),
('CAT007', N'Áo polo'),
('CAT008', N'Quần short'),
('CAT009', N'Đồ thể thao'),
('CAT010', N'Phụ kiện');
GO

-- 4. brands
INSERT INTO brands (brand_name, logo) VALUES
(N'Nike', N'/uploads/brands/nike.png'),
(N'Adidas', N'/uploads/brands/adidas.png'),
(N'Uniqlo', N'/uploads/brands/uniqlo.png'),
(N'Puma', N'/uploads/brands/puma.png'),
(N'Levi''s', N'/uploads/brands/levis.png'),
(N'H&M', N'/uploads/brands/hm.png'),
(N'Zara', N'/uploads/brands/zara.png'),
(N'Converse', N'/uploads/brands/converse.png'),
(N'Champion', N'/uploads/brands/champion.png'),
(N'Routine', N'/uploads/brands/routine.png');
GO

-- 5. colors
INSERT INTO colors (color_name, color_code) VALUES
(N'Đen', '#000000'),
(N'Trắng', '#FFFFFF'),
(N'Đỏ', '#FF0000'),
(N'Xanh dương', '#0000FF'),
(N'Xanh lá', '#008000'),
(N'Vàng', '#FFFF00'),
(N'Xám', '#808080'),
(N'Cam', '#FFA500'),
(N'Nâu', '#A52A2A'),
(N'Hồng', '#FFC0CB');
GO

-- 6. sizes
INSERT INTO sizes (size_name) VALUES
(N'XS'), (N'S'), (N'M'), (N'L'), (N'XL'),
(N'XXL'), (N'28'), (N'30'), (N'32'), (N'34');
GO

-- 7. material
INSERT INTO material (material_name) VALUES
(N'Cotton'),
(N'Polyester'),
(N'Elastane'),
(N'Len'),
(N'Kaki'),
(N'Denim'),
(N'Nylon'),
(N'Rayon'),
(N'Linen'),
(N'Viscose');
GO

-- 8. discounts
INSERT INTO discounts
(discount_id, discount_value, discount_type, start_date, end_date, status, description, condition)
VALUES
('DISC001', 10, N'PERCENT', '2026-09-01', '2026-09-30', N'ACTIVE', N'Giảm 10%', 0),
('DISC002', 15, N'PERCENT', '2026-09-01', '2026-09-30', N'ACTIVE', N'Giảm 15%', 0),
('DISC003', 20, N'PERCENT', '2026-09-01', '2026-09-30', N'ACTIVE', N'Giảm 20%', 0),
('DISC004', 50000, N'AMOUNT', '2026-09-01', '2026-09-30', N'ACTIVE', N'Giảm 50000', 500000),
('DISC005', 100000, N'AMOUNT', '2026-09-01', '2026-09-30', N'ACTIVE', N'Giảm 100000', 800000),
('DISC006', 25, N'PERCENT', '2026-10-01', '2026-10-31', N'UPCOMING', N'Giảm 25%', 0),
('DISC007', 30, N'PERCENT', '2026-10-01', '2026-10-31', N'UPCOMING', N'Giảm 30%', 0),
('DISC008', 50000, N'AMOUNT', '2026-10-01', '2026-10-31', N'UPCOMING', N'Giảm 50000', 300000),
('DISC009', 10, N'PERCENT', '2026-08-01', '2026-08-31', N'EXPIRED', N'Khuyến mãi tháng 8', 0),
('DISC010', 200000, N'AMOUNT', '2026-08-01', '2026-08-31', N'EXPIRED', N'Giảm 200000', 1000000);
GO

-- 9. shop_menus
INSERT INTO shop_menus
(menu_id, upper_menu_id, menu_name, link_uri, display_order, use_yn, lev, description)
VALUES
('MENU001', NULL, N'Trang chủ', N'/', 1, N'Y', 1, N'Trang chủ'),
('MENU002', NULL, N'Sản phẩm', N'/products', 2, N'Y', 1, N'Danh sách sản phẩm'),
('MENU003', NULL, N'Đơn hàng', N'/orders', 3, N'Y', 1, N'Quản lý đơn hàng'),
('MENU004', NULL, N'Khách hàng', N'/users', 4, N'Y', 1, N'Quản lý khách hàng'),
('MENU005', NULL, N'Khuyến mãi', N'/discounts', 5, N'Y', 1, N'Quản lý khuyến mãi'),
('MENU006', 'MENU002', N'Danh mục', N'/categories', 1, N'Y', 2, N'Quản lý danh mục'),
('MENU007', 'MENU002', N'Thương hiệu', N'/brands', 2, N'Y', 2, N'Quản lý thương hiệu'),
('MENU008', 'MENU003', N'Chi tiết đơn hàng', N'/order-items', 1, N'Y', 2, N'Chi tiết đơn hàng'),
('MENU009', NULL, N'Giỏ hàng', N'/cart', 6, N'Y', 1, N'Giỏ hàng'),
('MENU010', NULL, N'Bán hàng POS', N'/pos', 7, N'Y', 1, N'Bán hàng tại quầy');
GO

-- 10. menu_role
INSERT INTO menu_role (role_id, menu_id) VALUES
('ROLE001', 'MENU001'),
('ROLE001', 'MENU002'),
('ROLE001', 'MENU003'),
('ROLE001', 'MENU004'),
('ROLE001', 'MENU005'),
('ROLE002', 'MENU001'),
('ROLE002', 'MENU002'),
('ROLE002', 'MENU009'),
('ROLE003', 'MENU001'),
('ROLE003', 'MENU010');
GO

-- 11. products
INSERT INTO products
(product_id, product_name, description, category_id, discount_price, unit_price, is_discount, is_special, brand_id, weight, created_date, created_by, updated_date, updated_by, thumbnail)
VALUES
('PROD001', N'Áo thun Basic Đen', N'Áo thun cotton cơ bản', 'CAT001', 180000, 200000, 1, 0, 1, 0.25, GETDATE(), 'USR001', GETDATE(), 'USR001', N'/uploads/products/prod001.jpg'),
('PROD002', N'Áo sơ mi Oxford', N'Áo sơ mi công sở', 'CAT002', 350000, 400000, 1, 1, 3, 0.35, GETDATE(), 'USR001', GETDATE(), 'USR001', N'/uploads/products/prod002.jpg'),
('PROD003', N'Quần jean Slim', N'Quần jean dáng slim', 'CAT003', 450000, 500000, 1, 0, 5, 0.60, GETDATE(), 'USR001', GETDATE(), 'USR001', N'/uploads/products/prod003.jpg'),
('PROD004', N'Quần kaki Classic', N'Quần kaki nam', 'CAT004', 320000, 350000, 1, 0, 10, 0.55, GETDATE(), 'USR001', GETDATE(), 'USR001', N'/uploads/products/prod004.jpg'),
('PROD005', N'Áo khoác thể thao', N'Áo khoác nhẹ', 'CAT005', 650000, 750000, 1, 1, 2, 0.70, GETDATE(), 'USR001', GETDATE(), 'USR001', N'/uploads/products/prod005.jpg'),
('PROD006', N'Váy nữ thanh lịch', N'Váy nữ thời trang', 'CAT006', 480000, 550000, 1, 0, 7, 0.45, GETDATE(), 'USR001', GETDATE(), 'USR001', N'/uploads/products/prod006.jpg'),
('PROD007', N'Áo polo Classic', N'Áo polo cotton', 'CAT007', 300000, 350000, 1, 0, 9, 0.30, GETDATE(), 'USR001', GETDATE(), 'USR001', N'/uploads/products/prod007.jpg'),
('PROD008', N'Quần short Sport', N'Quần short thể thao', 'CAT008', 220000, 250000, 1, 0, 4, 0.25, GETDATE(), 'USR001', GETDATE(), 'USR001', N'/uploads/products/prod008.jpg'),
('PROD009', N'Bộ đồ thể thao', N'Bộ thể thao nam', 'CAT009', 550000, 650000, 1, 1, 1, 0.65, GETDATE(), 'USR001', GETDATE(), 'USR001', N'/uploads/products/prod009.jpg'),
('PROD010', N'Mũ lưỡi trai', N'Mũ thời trang', 'CAT010', 150000, 180000, 1, 0, 8, 0.15, GETDATE(), 'USR001', GETDATE(), 'USR001', N'/uploads/products/prod010.jpg');
GO

-- 12. product_details
INSERT INTO product_details
(product_detail_id, product_id, color_id, size_id, quantity, description)
VALUES
('PD001', 'PROD001', 1, 3, 100, N'Áo đen size M'),
('PD002', 'PROD002', 2, 4, 80, N'Áo trắng size L'),
('PD003', 'PROD003', 4, 8, 60, N'Quần xanh size 30'),
('PD004', 'PROD004', 7, 9, 70, N'Quần xám size 32'),
('PD005', 'PROD005', 1, 5, 50, N'Áo khoác đen size XL'),
('PD006', 'PROD006', 10, 4, 40, N'Váy hồng size L'),
('PD007', 'PROD007', 3, 3, 90, N'Polo đỏ size M'),
('PD008', 'PROD008', 4, 9, 75, N'Quần short xanh size 32'),
('PD009', 'PROD009', 5, 5, 45, N'Bộ thể thao xanh lá size XL'),
('PD010', 'PROD010', 6, 2, 120, N'Mũ vàng size S');
GO

-- 13. product_images
INSERT INTO product_images
(image_id, product_detail_id, image_url, is_thumbnail)
VALUES
('IMG001', 'PD001', N'/uploads/products/prod001-1.jpg', 1),
('IMG002', 'PD002', N'/uploads/products/prod002-1.jpg', 1),
('IMG003', 'PD003', N'/uploads/products/prod003-1.jpg', 1),
('IMG004', 'PD004', N'/uploads/products/prod004-1.jpg', 1),
('IMG005', 'PD005', N'/uploads/products/prod005-1.jpg', 1),
('IMG006', 'PD006', N'/uploads/products/prod006-1.jpg', 1),
('IMG007', 'PD007', N'/uploads/products/prod007-1.jpg', 1),
('IMG008', 'PD008', N'/uploads/products/prod008-1.jpg', 1),
('IMG009', 'PD009', N'/uploads/products/prod009-1.jpg', 1),
('IMG010', 'PD010', N'/uploads/products/prod010-1.jpg', 1);
GO

-- 14. product_materials
INSERT INTO product_materials (product_id, material_id, percentage) VALUES
('PROD001', 1, 100.00),
('PROD002', 1, 70.00),
('PROD003', 6, 98.00),
('PROD004', 5, 100.00),
('PROD005', 2, 80.00),
('PROD006', 8, 90.00),
('PROD007', 1, 95.00),
('PROD008', 2, 90.00),
('PROD009', 2, 85.00),
('PROD010', 7, 100.00);
GO

-- 15. orders
INSERT INTO orders
(order_id, user_id, discount_id, total_price, status, created_date, created_by, updated_date, updated_by)
VALUES
('ORD001', 'USR002', 'DISC001', 180000, N'COMPLETED', GETDATE(), 'USR008', GETDATE(), 'USR008'),
('ORD002', 'USR003', 'DISC002', 350000, N'COMPLETED', GETDATE(), 'USR008', GETDATE(), 'USR008'),
('ORD003', 'USR002', NULL, 450000, N'PENDING', GETDATE(), 'USR008', GETDATE(), 'USR008'),
('ORD004', 'USR003', 'DISC003', 320000, N'CONFIRMED', GETDATE(), 'USR008', GETDATE(), 'USR008'),
('ORD005', 'USR002', NULL, 650000, N'SHIPPING', GETDATE(), 'USR008', GETDATE(), 'USR008'),
('ORD006', 'USR003', 'DISC004', 480000, N'COMPLETED', GETDATE(), 'USR008', GETDATE(), 'USR008'),
('ORD007', 'USR002', NULL, 300000, N'PENDING', GETDATE(), 'USR008', GETDATE(), 'USR008'),
('ORD008', 'USR003', 'DISC005', 220000, N'CONFIRMED', GETDATE(), 'USR008', GETDATE(), 'USR008'),
('ORD009', 'USR002', 'DISC001', 550000, N'SHIPPING', GETDATE(), 'USR008', GETDATE(), 'USR008'),
('ORD010', 'USR003', NULL, 150000, N'COMPLETED', GETDATE(), 'USR008', GETDATE(), 'USR008');
GO

-- 16. order_items
INSERT INTO order_items
(order_id, product_detail_id, quantity, unit_price, discount_price, total_price)
VALUES
('ORD001', 'PD001', 1, 200000, 180000, 180000),
('ORD002', 'PD002', 1, 400000, 350000, 350000),
('ORD003', 'PD003', 1, 500000, 450000, 450000),
('ORD004', 'PD004', 1, 350000, 320000, 320000),
('ORD005', 'PD005', 1, 750000, 650000, 650000),
('ORD006', 'PD006', 1, 550000, 480000, 480000),
('ORD007', 'PD007', 1, 350000, 300000, 300000),
('ORD008', 'PD008', 1, 250000, 220000, 220000),
('ORD009', 'PD009', 1, 650000, 550000, 550000),
('ORD010', 'PD010', 1, 180000, 150000, 150000);
GO

-- 17. shipments
INSERT INTO shipments
(order_id, user_id, shipping_address, shipping_method, tracking_number, shipment_status, shipped_date, delivered_date, created_at, updated_at)
VALUES
('ORD001', 'USR002', N'Bắc Ninh', N'Giao hàng tiêu chuẩn', N'TRACK001', N'DELIVERED', GETDATE(), GETDATE(), GETDATE(), GETDATE()),
('ORD002', 'USR003', N'Hà Nội', N'Giao hàng tiêu chuẩn', N'TRACK002', N'DELIVERED', GETDATE(), GETDATE(), GETDATE(), GETDATE()),
('ORD003', 'USR002', N'Bắc Ninh', N'Giao hàng nhanh', N'TRACK003', N'PENDING', NULL, NULL, GETDATE(), GETDATE()),
('ORD004', 'USR003', N'Hà Nội', N'Giao hàng tiêu chuẩn', N'TRACK004', N'CONFIRMED', NULL, NULL, GETDATE(), GETDATE()),
('ORD005', 'USR002', N'Bắc Ninh', N'Giao hàng nhanh', N'TRACK005', N'SHIPPING', GETDATE(), NULL, GETDATE(), GETDATE()),
('ORD006', 'USR003', N'Hà Nội', N'Giao hàng tiêu chuẩn', N'TRACK006', N'DELIVERED', GETDATE(), GETDATE(), GETDATE(), GETDATE()),
('ORD007', 'USR002', N'Bắc Ninh', N'Giao hàng tiêu chuẩn', N'TRACK007', N'PENDING', NULL, NULL, GETDATE(), GETDATE()),
('ORD008', 'USR003', N'Hà Nội', N'Giao hàng nhanh', N'TRACK008', N'CONFIRMED', NULL, NULL, GETDATE(), GETDATE()),
('ORD009', 'USR002', N'Bắc Ninh', N'Giao hàng nhanh', N'TRACK009', N'SHIPPING', GETDATE(), NULL, GETDATE(), GETDATE()),
('ORD010', 'USR003', N'Hà Nội', N'Giao hàng tiêu chuẩn', N'TRACK010', N'DELIVERED', GETDATE(), GETDATE(), GETDATE(), GETDATE());
GO

-- 18. cart_items
INSERT INTO cart_items
(cart_id, user_id, product_detail_id, quantity, created_date, created_by, updated_date, updated_by)
VALUES
('CART001', 'USR002', 'PD001', 2, GETDATE(), 'USR002', GETDATE(), 'USR002'),
('CART002', 'USR003', 'PD002', 1, GETDATE(), 'USR003', GETDATE(), 'USR003'),
('CART003', 'USR002', 'PD003', 1, GETDATE(), 'USR002', GETDATE(), 'USR002'),
('CART004', 'USR003', 'PD004', 2, GETDATE(), 'USR003', GETDATE(), 'USR003'),
('CART005', 'USR002', 'PD005', 1, GETDATE(), 'USR002', GETDATE(), 'USR002'),
('CART006', 'USR003', 'PD006', 1, GETDATE(), 'USR003', GETDATE(), 'USR003'),
('CART007', 'USR002', 'PD007', 3, GETDATE(), 'USR002', GETDATE(), 'USR002'),
('CART008', 'USR003', 'PD008', 1, GETDATE(), 'USR003', GETDATE(), 'USR003'),
('CART009', 'USR002', 'PD009', 1, GETDATE(), 'USR002', GETDATE(), 'USR002'),
('CART010', 'USR003', 'PD010', 2, GETDATE(), 'USR003', GETDATE(), 'USR003');
GO

-- 19. common_codes
INSERT INTO common_codes
(comm_cd, comm_nm, description, lev, up_comm_cd, use_yn, value_config)
VALUES
(N'ST001', N'Đang hoạt động', N'Trạng thái hoạt động', 1, NULL, N'Y', N'ACTIVE'),
(N'ST002', N'Không hoạt động', N'Trạng thái không hoạt động', 1, NULL, N'Y', N'INACTIVE'),
(N'OD001', N'Chờ xử lý', N'Trạng thái đơn hàng', 1, NULL, N'Y', N'PENDING'),
(N'OD002', N'Đã xác nhận', N'Trạng thái đơn hàng', 1, NULL, N'Y', N'CONFIRMED'),
(N'OD003', N'Đang giao', N'Trạng thái đơn hàng', 1, NULL, N'Y', N'SHIPPING'),
(N'OD004', N'Hoàn thành', N'Trạng thái đơn hàng', 1, NULL, N'Y', N'COMPLETED'),
(N'OD005', N'Đã hủy', N'Trạng thái đơn hàng', 1, NULL, N'Y', N'CANCELLED'),
(N'SP001', N'Chờ giao', N'Trạng thái vận chuyển', 1, NULL, N'Y', N'PENDING'),
(N'SP002', N'Đang giao', N'Trạng thái vận chuyển', 1, NULL, N'Y', N'SHIPPING'),
(N'SP003', N'Đã giao', N'Trạng thái vận chuyển', 1, NULL, N'Y', N'DELIVERED');
GO

-- Kiểm tra số lượng bản ghi mỗi bảng
SELECT 'brands' AS table_name, COUNT(*) AS total FROM brands
UNION ALL SELECT 'cart_items', COUNT(*) FROM cart_items
UNION ALL SELECT 'categories', COUNT(*) FROM categories
UNION ALL SELECT 'colors', COUNT(*) FROM colors
UNION ALL SELECT 'common_codes', COUNT(*) FROM common_codes
UNION ALL SELECT 'discounts', COUNT(*) FROM discounts
UNION ALL SELECT 'material', COUNT(*) FROM material
UNION ALL SELECT 'menu_role', COUNT(*) FROM menu_role
UNION ALL SELECT 'order_items', COUNT(*) FROM order_items
UNION ALL SELECT 'orders', COUNT(*) FROM orders
UNION ALL SELECT 'product_details', COUNT(*) FROM product_details
UNION ALL SELECT 'product_images', COUNT(*) FROM product_images
UNION ALL SELECT 'product_materials', COUNT(*) FROM product_materials
UNION ALL SELECT 'products', COUNT(*) FROM products
UNION ALL SELECT 'roles', COUNT(*) FROM roles
UNION ALL SELECT 'shipments', COUNT(*) FROM shipments
UNION ALL SELECT 'shop_menus', COUNT(*) FROM shop_menus
UNION ALL SELECT 'sizes', COUNT(*) FROM sizes
UNION ALL SELECT 'users', COUNT(*) FROM users;
GO

select * from users
select * from roles
select * from menu_role

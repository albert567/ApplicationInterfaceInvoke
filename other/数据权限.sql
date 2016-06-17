/* 为了防止任何可能出现的数据丢失问题，您应该先仔细检查此脚本，然后再在数据库设计器的上下文之外运行此脚本。*/
BEGIN TRANSACTION
SET QUOTED_IDENTIFIER ON
SET ARITHABORT ON
SET NUMERIC_ROUNDABORT OFF
SET CONCAT_NULL_YIELDS_NULL ON
SET ANSI_NULLS ON
SET ANSI_PADDING ON
SET ANSI_WARNINGS ON
COMMIT
BEGIN TRANSACTION
GO
CREATE TABLE dbo.T_Role
	(
	ID int NOT NULL IDENTITY (1, 1),
	NAME nvarchar(50) NOT NULL,
	Description nvarchar(500) NULL
	)  ON [PRIMARY]
GO
ALTER TABLE dbo.T_Role ADD CONSTRAINT
	PK_T_Role PRIMARY KEY CLUSTERED
	(
	ID
	) WITH( STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]

GO
ALTER TABLE dbo.T_Role SET (LOCK_ESCALATION = TABLE)
GO
CREATE TABLE dbo.T_RoleUser
	(
	ID int NOT NULL IDENTITY (1, 1),
	RoleID int NOT NULL,
	UserID nvarchar(36) NOT NULL
	)  ON [PRIMARY]
GO
ALTER TABLE dbo.T_RoleUser ADD CONSTRAINT
	PK_T_RoleUser PRIMARY KEY CLUSTERED
	(
	ID
	) WITH( STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]

GO
ALTER TABLE dbo.T_RoleUser SET (LOCK_ESCALATION = TABLE)
GO
CREATE TABLE dbo.T_RoleRight
	(
	ID int NOT NULL IDENTITY (1, 1),
	RoleID int NOT NULL,
	MenuID int NOT NULL,
	DataID int NOT NULL,
	detailID int NULL
	)  ON [PRIMARY]
GO
ALTER TABLE dbo.T_RoleRight ADD CONSTRAINT
	PK_T_RoleRight PRIMARY KEY CLUSTERED
	(
	ID
	) WITH( STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]

GO
ALTER TABLE dbo.T_RoleRight SET (LOCK_ESCALATION = TABLE)
GO
COMMIT


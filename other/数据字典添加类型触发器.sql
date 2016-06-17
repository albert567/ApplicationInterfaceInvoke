alter table T_Dict alter column DKey varchar(4)
go

-- ================================================
-- Template generated from Template Explorer using:
-- Create Trigger (New Menu).SQL
--
-- Use the Specify Values for Template Parameters
-- command (Ctrl-Shift-M) to fill in the parameter
-- values below.
--
-- See additional Create Trigger templates for more
-- examples of different Trigger statements.
--
-- This block of comments will not be included in
-- the definition of the function.
-- ================================================
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE TRIGGER T_Dict_DKey_Insert
   ON  T_Dict
   AFTER INSERT
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;
    -- Insert statements for trigger here
	 --�������
    declare @id int, @dtype varchar(2);
    --��inserted���в�ѯ�Ѿ������¼��Ϣ
    select @id = id, @dtype = dtype from inserted;
    if(@dtype<>'00')
    begin
		update T_Dict set DKey=(select right('0'+rtrim(max(dkey)+1),4) from T_Dict where DType=@dtype) where ID=@id
    end
END
GO


CREATE PROCEDURE aktivdolgozok(
	@visszamenolegnapokig INT
)
AS
BEGIN
SELECT [DOLG_ID]
      ,[TORZSSZAM]
      ,[NEV]
      ,[ADOSZAM]
	  ,[BELEPES]
      ,[KILEPES]
      ,[EREDETI_TORZSSZAM]
 FROM [ziehlabegg_webiv_4].[dbo].[NEZET_01] WHERE DATEDIFF(dd,DATEADD(dd,@visszamenolegnapokig,GETDATE()),CONVERT(datetime,[KILEPES]))>0  ORDER BY CONVERT(datetime,[KILEPES])
 END
 GO

 DECLARE @egyhonap int = 31
 EXEC aktivdolgozok @visszamenolegnapokig=@egyhonap

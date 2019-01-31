USE KOZTES_WEBIV_4;
GO

DECLARE @visszamenolegnapokig int = -31
BEGIN
SELECT n1.[DOLG_ID]
      ,n1.[TORZSSZAM]
      ,n1.[NEV]
      ,n1.[ADOSZAM]
	  ,CASE n4.[NEV]
	  ,n5.[MUNKAKOR]
      ,n1.[EREDETI_TORZSSZAM]
 FROM [10.9.80.200].ziehlabegg_webiv_4.dbo.NEZET_01 n1 WHERE DATEDIFF(dd,DATEADD(dd,@visszamenolegnapokig,GETDATE()),CONVERT(datetime,n1.[KILEPES]))>0  ORDER BY CONVERT(datetime,n1.[KILEPES])
 END
 GO
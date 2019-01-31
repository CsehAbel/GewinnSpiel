USE KOZTES_WEBIV_4
GO

DECLARE @visszamenolegnapokig int = -31
BEGIN
SELECT n1.[ADOSZAM]
FROM [10.9.80.200].[ziehlabegg_webiv_4].[dbo].[NEZET_01] n1 
WHERE DATEDIFF(dd,DATEADD(dd,@visszamenolegnapokig,GETDATE()),CONVERT(datetime,n1.[KILEPES]))>0

insert into
openquery
(
	LOKALIS, 'select adoszam from lokalis.adoszam'
) SELECT n1.[ADOSZAM]
FROM [10.9.80.200].[ziehlabegg_webiv_4].[dbo].[NEZET_01] n1 
WHERE DATEDIFF(dd,DATEADD(dd,@visszamenolegnapokig,GETDATE()),CONVERT(datetime,n1.[KILEPES]))>0

END
GO
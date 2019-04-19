/*USE KOZTES_WEBIV_4
GO

DECLARE @visszamenolegnapokig int = -31
BEGIN
SELECT n5.[TORZSSZ]
      ,n5.[KEZD]
      ,n5.[VEGE]
      ,n5.[KAPACITAS_CSOPORT]
      ,n5.[KAPACITAS_HELY]
      ,n5.[UZEMEGYSEG]
      ,n5.[KOLTSEGHELY]
      ,n5.[MUNKAKOR]
      ,n5.[MUNKAKOR_KOD]
  FROM [10.9.80.200].[ziehlabegg_webiv_4].[dbo].[NEZET_05] n5 WHERE DATEDIFF(dd,DATEADD(dd,@visszamenolegnapokig,GETDATE()),CONVERT(datetime,n5.[VEGE]))>0
  END
  GO*/
USE KOZTES_WEBIV_4
GO

DECLARE @visszamenolegnapokig int = -31
BEGIN
WITH maxKezdet(ADOSZAM,MAX_N1_BELEPES_DATETIME) AS(
SELECT [ADOSZAM],MAX(N1_BELEPES_DATETIME) MAX_N1_BELEPES_DATETIME FROM(
	SELECT n1.[ADOSZAM]
		  ,CONVERT(datetime,n1.[BELEPES]) as N1_BELEPES_DATETIME
	  FROM [10.9.80.200].[ziehlabegg_webiv_4].[dbo].[NEZET_01] n1 WHERE DATEDIFF(dd,DATEADD(dd,@visszamenolegnapokig,GETDATE()),CONVERT(datetime,n1.[KILEPES]))>0 ) as m GROUP BY [ADOSZAM])

--SELECT x.[NEV] FROM (SELECT m.ADOSZAM,n1.[TORZSSZAM],n1.[NEV],m.MAX_N1_BELEPES_DATETIME FROM maxKezdet m JOIN  [10.9.80.200].[ziehlabegg_webiv_4].[dbo].[NEZET_01] n1 ON m.ADOSZAM=n1.[ADOSZAM] AND m.MAX_N1_BELEPES_DATETIME=CONVERT(datetime,n1.[BELEPES]) ) x
insert into
openquery
(
	LOKALIS, 'select adoszam,torzsszam,nev,uzemegyseg,munkakor from lokalis.dolgozo_duplicate'
) SELECT x.ADOSZAM,x.[TORZSSZAM],x.[NEV],n4.[NEV],n5.[MUNKAKOR] FROM (SELECT m.ADOSZAM,n1.[TORZSSZAM],n1.[NEV],m.MAX_N1_BELEPES_DATETIME FROM maxKezdet m JOIN  [10.9.80.200].[ziehlabegg_webiv_4].[dbo].[NEZET_01] n1 ON m.ADOSZAM=n1.[ADOSZAM] AND m.MAX_N1_BELEPES_DATETIME=CONVERT(datetime,n1.[BELEPES])) x JOIN [10.9.80.200].[ziehlabegg_webiv_4].[dbo].[NEZET_05] n5 ON x.[TORZSSZAM]=n5.TORZSSZ AND x.MAX_N1_BELEPES_DATETIME=CONVERT(datetime,n5.[KEZD]) inner join [10.9.80.200].ziehlabegg_webiv_4.dbo.NEZET_04 n4 
			on n4.EGYSEG_KOD = n5.UZEMEGYSEG
END
GO
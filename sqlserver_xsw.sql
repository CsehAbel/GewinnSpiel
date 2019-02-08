USE KOZTES_WEBIV_4
GO

BEGIN
insert into
openquery
(
	LOKALIS, 'select kartyaszam,dolgozokod,nev from lokalis.xsw_duplicate'
)
SELECT NUMBER,CODE,NAM FROM OPENQUERY (XSW,'SELECT  card_0.NUMBER,users_0.CODE, users_0.NAME as NAM
FROM card card_0, users users_0
WHERE card_0.USER_ID = users_0.ID  AND users_0.NAME NOT LIKE "%Master%";')
END
GO
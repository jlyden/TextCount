-- =======================================================================================================
--  Author:			Jennifer Lyden
--	Create Date:	2017-08-03
--  Updated:		2017-10-12
--	Description:	Removes Content Import information from Tracbook Sync 'Staging' tables
--  WARNING:        If there is complete data in these tables in Production, you probably don't want to delete it
-- =======================================================================================================

DECLARE @DoIt BIT = 0
DECLARE @GeographicalCategoryId integer = ; -- TODO: Set GC to delete from

-- Verify what you are going to delete
SELECT * FROM TracbookSync.Gemini.Exam WHERE GeographicalCategoryId = @GeographicalCategoryId;
SELECT * FROM TracbookSync.Gemini.ExamItem WHERE GeographicalCategoryId = @GeographicalCategoryId;
SELECT * FROM TracbookSync.Gemini.Feature WHERE GeographicalCategoryId = @GeographicalCategoryId;
SELECT * FROM TracbookSync.Gemini.FeatureItem WHERE GeographicalCategoryId = @GeographicalCategoryId;
SELECT * FROM TracbookSync.Gemini.Item WHERE GeographicalCategoryId = @GeographicalCategoryId;
SELECT * FROM TracbookSync.Gemini.Passage WHERE GeographicalCategoryId = @GeographicalCategoryId;
SELECT * FROM TracbookSync.Gemini.PassageFeatureCategory WHERE GeographicalCategoryId = @GeographicalCategoryId;
SELECT * FROM TracbookSync.Gemini.Question WHERE GeographicalCategoryId = @GeographicalCategoryId;
SELECT * FROM TracbookSync.Gemini.StandardQuestion WHERE GeographicalCategoryId = @GeographicalCategoryId;
SELECT * FROM TracbookSync.Gemini.SubjectItem WHERE GeographicalCategoryId = @GeographicalCategoryId;
SELECT * FROM TracbookSync.Gemini.SubjectPassage WHERE GeographicalCategoryId = @GeographicalCategoryId;
SELECT * FROM TracbookSync.Learnosity.Item WHERE GeographicalCategoryId = @GeographicalCategoryId;
SELECT * FROM TracbookSync.Learnosity.Passage WHERE GeographicalCategoryId = @GeographicalCategoryId;
SELECT * FROM TracbookSync.Learnosity.Question WHERE GeographicalCategoryId = @GeographicalCategoryId;

-- Run the deletes 
BEGIN TRANSACTION
	DELETE FROM TracbookSync.Gemini.Exam WHERE GeographicalCategoryId = @GeographicalCategoryId;
	DELETE FROM TracbookSync.Gemini.ExamItem WHERE GeographicalCategoryId = @GeographicalCategoryId;
	DELETE FROM TracbookSync.Gemini.Feature WHERE GeographicalCategoryId = @GeographicalCategoryId;
	DELETE FROM TracbookSync.Gemini.FeatureItem WHERE GeographicalCategoryId = @GeographicalCategoryId;
	DELETE FROM TracbookSync.Gemini.Item WHERE GeographicalCategoryId = @GeographicalCategoryId;
	DELETE FROM TracbookSync.Gemini.Passage WHERE GeographicalCategoryId = @GeographicalCategoryId;
	DELETE FROM TracbookSync.Gemini.PassageFeatureCategory WHERE GeographicalCategoryId = @GeographicalCategoryId;
	DELETE FROM TracbookSync.Gemini.Question WHERE GeographicalCategoryId = @GeographicalCategoryId;
	DELETE FROM TracbookSync.Gemini.StandardQuestion WHERE GeographicalCategoryId = @GeographicalCategoryId;
	DELETE FROM TracbookSync.Gemini.SubjectItem WHERE GeographicalCategoryId = @GeographicalCategoryId;
	DELETE FROM TracbookSync.Gemini.SubjectPassage WHERE GeographicalCategoryId = @GeographicalCategoryId;
	DELETE FROM TracbookSync.Learnosity.Item WHERE GeographicalCategoryId = @GeographicalCategoryId;
	DELETE FROM TracbookSync.Learnosity.Passage WHERE GeographicalCategoryId = @GeographicalCategoryId;
	DELETE FROM TracbookSync.Learnosity.Question WHERE GeographicalCategoryId = @GeographicalCategoryId;

	IF @DoIt = 1
		-- Sanity check - Remove if you really want to delete from Production
		IF @@ServerName = 'CLT-PRD-SQL01\PRDSQL01'
			PRINT 'Are you sure you want to delete this from Prod?';
		ELSE
			COMMIT TRANSACTION
	ELSE
		ROLLBACK TRANSACTION

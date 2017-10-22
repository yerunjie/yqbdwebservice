ALTER TABLE `company_info`
  ADD COLUMN `head_portrait_address` VARCHAR(255) DEFAULT ''
  AFTER `summary`;
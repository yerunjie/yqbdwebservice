ALTER TABLE `task`
  ADD COLUMN `primary_work` VARCHAR(255) DEFAULT ''
  AFTER `task_address`,
  ADD COLUMN `other_company` VARCHAR(255) DEFAULT ''
  AFTER `primary_work`,
  ADD COLUMN `primary_contact` VARCHAR(255) DEFAULT ''
  AFTER `other_company`;
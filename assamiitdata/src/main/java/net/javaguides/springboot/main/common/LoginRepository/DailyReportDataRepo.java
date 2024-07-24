package net.javaguides.springboot.main.common.LoginRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.springboot.main.common.model.DailyReportDataModel;

public interface DailyReportDataRepo  extends JpaRepository<DailyReportDataModel, String>{

}

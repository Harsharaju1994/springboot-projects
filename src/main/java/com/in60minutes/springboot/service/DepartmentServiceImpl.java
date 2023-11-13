package com.in60minutes.springboot.service;

import com.in60minutes.springboot.entity.Department;
import com.in60minutes.springboot.exception.DepartmentNotFoundException;
import com.in60minutes.springboot.repository.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {



    @Autowired
    private DepartmentRepo departmentRepo;

    //post

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepo.save(department);
    }

    //GetAll

    @Override
    public List<Department> fetchDepartmentList() {
        return departmentRepo.findAll();
    }

    //FetchById

    @Override
    public Department fetchdepartmentById(Long departmentId) throws DepartmentNotFoundException {
        Optional<Department> department =
                departmentRepo.findById(departmentId);

        if(!department.isPresent()){
            throw new DepartmentNotFoundException("Department Not Available");
        }
        return department.get();
    }

    //DeleteById

    @Override
    public void deleteDepartmentById(Long departmentId) {
        departmentRepo.deleteById(departmentId);
    }

    //Update

    @Override
    public Department updateDepartment(Long departmentId, Department department) {
        Department depDB = departmentRepo.findById(departmentId).get();
        if(Objects.nonNull(department.getDepartmentName())&&
        !"".equalsIgnoreCase(department.getDepartmentName())){
            depDB.setDepartmentName((department.getDepartmentName()));
        }

        if(Objects.nonNull(department.getDepartmentCode())&&
                !"".equalsIgnoreCase(department.getDepartmentCode())){
            depDB.setDepartmentCode((department.getDepartmentCode()));
        }

        if(Objects.nonNull(department.getDepartmentAddress())&&
                !"".equalsIgnoreCase(department.getDepartmentAddress())){
            depDB.setDepartmentAddress((department.getDepartmentAddress()));
        }
        return departmentRepo.save(depDB);
    }

    //UpdateByName

    @Override
    public Department fetchDepartmentByName(String departmentName) {
        return departmentRepo.findByDepartmentNameIgnoreCase(departmentName);
    }


}

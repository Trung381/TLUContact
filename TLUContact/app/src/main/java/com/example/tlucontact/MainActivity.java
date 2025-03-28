package com.example.tlucontact;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button btnDepartments, btnStaff;
    private SearchView searchView;
    private Button btnSort;
    private boolean isSortedAscending = true;

    private List<Department> departmentList;
    private List<Staff> staffList;

    private DepartmentAdapter departmentAdapter;
    private StaffAdapter staffAdapter;
    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // liên kết biến với các view
        recyclerView = findViewById(R.id.recyclerView);
        btnDepartments = findViewById(R.id.btnDepartments);
        btnStaff = findViewById(R.id.btnStaff);
        searchView = findViewById(R.id.searchView);
        btnSort = findViewById(R.id.btnSort);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseManager = new DatabaseManager(this);
        departmentList = databaseManager.getAllDepartments();
        staffList = databaseManager.getAllStaff();



        // tạo adapter với context chuẩn bị dữ liệu cho recyclerView
        departmentAdapter = new DepartmentAdapter(departmentList, this);
        staffAdapter = new StaffAdapter(staffList, this);

        // set hiển thị mặc định với departmentAdapter
        recyclerView.setAdapter(departmentAdapter);

        // hàm click vào để hiển thị department
        btnDepartments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setAdapter(departmentAdapter);
                updateSearchHint("Tìm phòng ban...");
                btnSort.setText("Z-A");
            }
        });

        // hàm click vào để hiển thị staff
        btnStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setAdapter(staffAdapter);
                updateSearchHint("Tìm CBNV...");
                btnSort.setText("A-Z");
            }
        });

        // hàm tìm kiếm, gọi đến hàm filter được định nghĩa trong DepartmentAdapter và StaffAdapter
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // không cần xử lý, vì dữ liệu được lọc theo mỗi ký tự nhập vào
                // trả về false sẽ thực hiện hành động mặc định của searchView
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (recyclerView.getAdapter() == departmentAdapter) {
                    departmentAdapter.filter(newText);
                } else {
                    staffAdapter.filter(newText);
                }
                return true;
            }
        });

        // xử lý sự kiện click vào nút sort
        btnSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recyclerView.getAdapter() == departmentAdapter) {
                    sortDepartments();
                } else {
                    sortStaff();
                }
            }
        });
    }

    private void updateSearchHint(String hint) {
        searchView.setQueryHint(hint);
    }

    private void sortDepartments() {
        isSortedAscending = !isSortedAscending; // Đảo ngược trạng thái sắp xếp
        departmentAdapter.setSortedList(departmentList, isSortedAscending); // Gọi setSortedList từ adapter
        btnSort.setText(isSortedAscending ? "Z-A" : "A-Z"); // Cập nhật nút
    }

    private void sortStaff() {
        isSortedAscending = !isSortedAscending; // Đảo ngược trạng thái sắp xếp
        staffAdapter.setSortedList(staffList, isSortedAscending); // Gọi setSortedList từ adapter
        btnSort.setText(isSortedAscending ? "Z-A" : "A-Z"); // Cập nhật nút
    }
}


package com.example.tlucontact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "tlu_contact.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDepartmentTable = "CREATE TABLE department (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "phone TEXT, " +
                "address TEXT, " +
                "email TEXT, " +
                "image INTEGER)";

        String createStaffTable = "CREATE TABLE staff (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "position TEXT, " +
                "department TEXT, " +
                "phone TEXT, " +
                "email TEXT, " +
                "image INTEGER)";

        db.execSQL(createDepartmentTable);
        db.execSQL(createStaffTable);

        insertInitialData(db);
    }

    private void insertInitialData(SQLiteDatabase db) {
        // Thêm phòng ban
        insertDepartment(db, "Khoa Công nghệ thông tin", "024.38522028", "Tầng 2, Nhà C1", "cntt@tlu.edu.vn", R.drawable.img_department);
        insertDepartment(db, "Khoa Kinh tế và Quản lý", "024.35638252", "Tầng 6, Nhà A5", "ktql@tlu.edu.vn", R.drawable.img_department2);
        insertDepartment(db, "Khoa Kỹ thuật xây dựng", "024.35632211", "Tầng 3, Nhà A1", "ktxd@tlu.edu.vn", R.drawable.img_department);
        insertDepartment(db, "Phòng Đào tạo", "024.35632211", "Tầng 1, Nhà A1", "daotao@tlu.edu.vn", R.drawable.img_department);
        insertDepartment(db, "Phòng Công tác sinh viên", "024.35638364", "Tầng 1, Nhà A1", "ctsv@tlu.edu.vn", R.drawable.img_department);
        insertDepartment(db, "Phòng Kế hoạch - Tài chính", "024.38512345", "Tầng 2, Nhà C2", "khtc@tlu.edu.vn", R.drawable.img_department);
        insertDepartment(db, "Phòng Tổ chức cán bộ", "024.35678901", "Tầng 4, Nhà A3", "tccb@tlu.edu.vn", R.drawable.img_department);
        insertDepartment(db, "Phòng Hợp tác quốc tế", "024.38567890", "Tầng 5, Nhà A4", "htqt@tlu.edu.vn", R.drawable.img_department);
        insertDepartment(db, "Phòng Khoa học công nghệ", "024.35789123", "Tầng 6, Nhà A5", "khcn@tlu.edu.vn", R.drawable.img_department);
        insertDepartment(db, "Phòng Công nghệ thông tin", "024.38544567", "Tầng 7, Nhà C3", "cntt@tlu.edu.vn", R.drawable.img_department);
        insertDepartment(db, "Trung tâm Thư viện", "024.38599888", "Tầng 1, Nhà B1", "thuvien@tlu.edu.vn", R.drawable.img_department);
        insertDepartment(db, "Viện nghiên cứu Thủy lợi", "024.35711234", "Tầng 5, Nhà D2", "vientl@tlu.edu.vn", R.drawable.img_department);
        insertDepartment(db, "Khoa Môi trường", "024.35876543", "Tầng 3, Nhà E1", "moitruong@tlu.edu.vn", R.drawable.img_department);
        insertDepartment(db, "Khoa Điện - Điện tử", "024.35987654", "Tầng 2, Nhà F2", "dientu@tlu.edu.vn", R.drawable.img_department);
        insertDepartment(db, "Khoa Cơ khí", "024.36654321", "Tầng 4, Nhà G3", "cokhi@tlu.edu.vn", R.drawable.img_department);
        insertDepartment(db, "Trung tâm Giáo dục Quốc phòng", "024.37765432", "Tầng 1, Nhà H4", "giaoducqp@tlu.edu.vn", R.drawable.img_department);
        insertDepartment(db, "Trung tâm Ngoại ngữ", "024.38876543", "Tầng 2, Nhà I5", "ngoaingu@tlu.edu.vn", R.drawable.img_department);
        insertDepartment(db, "Phòng Thanh tra", "024.39987654", "Tầng 3, Nhà J6", "thanhtra@tlu.edu.vn", R.drawable.img_department);
        insertDepartment(db, "Ban Quản lý dự án", "024.31123456", "Tầng 4, Nhà K7", "bql@tlu.edu.vn", R.drawable.img_department);

        // Thêm nhân viên
        insertStaff(db, "TS. Nguyễn Thanh Tùng", "Trưởng khoa", "Khoa Công nghệ thông tin", "0912345678", "tungnt@tlu.edu.vn", R.drawable.img_person);
        insertStaff(db, "TS. Phạm Thị Hương", "Phó Trưởng khoa", "Khoa Công nghệ thông tin", "0923456789", "huongpt@tlu.edu.vn", R.drawable.img_person2);
        insertStaff(db, "PGS.TS. Trần Văn Nam", "Trưởng khoa", "Khoa Kinh tế và Quản lý", "0934567890", "namtv@tlu.edu.vn", R.drawable.img_person2);
        insertStaff(db, "ThS. Lê Thị Minh", "Giảng viên", "Khoa Kinh tế và Quản lý", "0945678901", "minhlt@tlu.edu.vn", R.drawable.img_person2);
        insertStaff(db, "PGS.TS. Đỗ Văn Hải", "Trưởng khoa", "Khoa Kỹ thuật xây dựng", "0956789012", "haidv@tlu.edu.vn", R.drawable.img_person2);
        insertStaff(db, "TS. Nguyễn Thị Lan", "Trưởng phòng", "Phòng Đào tạo", "0967890123", "lannt@tlu.edu.vn", R.drawable.img_person2);
        insertStaff(db, "ThS. Vũ Đình Tuấn", "Trưởng phòng", "Phòng Công tác sinh viên", "0978901234", "tuanvd@tlu.edu.vn", R.drawable.img_person2);
        insertStaff(db, "TS. Trần Văn Hùng", "Trưởng phòng", "Phòng Kế hoạch - Tài chính", "0989012345", "hungtv@tlu.edu.vn", R.drawable.img_person);
        insertStaff(db, "ThS. Nguyễn Văn Bình", "Giảng viên", "Khoa Điện - Điện tử", "0990123456", "binhnv@tlu.edu.vn", R.drawable.img_person);
        insertStaff(db, "PGS.TS. Hoàng Thị Thu", "Trưởng khoa", "Khoa Môi trường", "0911112233", "thuht@tlu.edu.vn", R.drawable.img_person2);
        insertStaff(db, "ThS. Đặng Minh Tuấn", "Trưởng khoa", "Khoa Cơ khí", "0922223344", "tuandm@tlu.edu.vn", R.drawable.img_person);
        insertStaff(db, "TS. Bùi Thị Lan", "Trưởng phòng", "Trung tâm Giáo dục Quốc phòng", "0933334455", "lanbt@tlu.edu.vn", R.drawable.img_person2);
        insertStaff(db, "ThS. Phạm Văn Đức", "Trưởng phòng", "Trung tâm Ngoại ngữ", "0944445566", "ducpv@tlu.edu.vn", R.drawable.img_person);
        insertStaff(db, "PGS.TS. Nguyễn Hữu Phước", "Trưởng phòng", "Phòng Thanh tra", "0955556677", "phuocnh@tlu.edu.vn", R.drawable.img_person2);
        insertStaff(db, "TS. Lê Minh An", "Trưởng ban", "Ban Quản lý dự án", "0966667788", "anlm@tlu.edu.vn", R.drawable.img_person);
    }

    private void insertDepartment(SQLiteDatabase db, String name, String phone, String address, String email, int image) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("phone", phone);
        values.put("address", address);
        values.put("email", email);
        values.put("image", image);
        db.insert("department", null, values);
    }

    private void insertStaff(SQLiteDatabase db, String name, String position, String department, String phone, String email, int image) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("position", position);
        values.put("department", department);
        values.put("phone", phone);
        values.put("email", email);
        values.put("image", image);
        db.insert("staff", null, values);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS department");
        db.execSQL("DROP TABLE IF EXISTS staff");
        onCreate(db);
    }
}

// StaffAdapter.java
package com.example.tlucontact;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class StaffAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private List<Object> items;
    private List<Staff> staffList; // Keep the original list
    private Context context;
    private boolean isAscending = true;

    public StaffAdapter(List<Staff> staffList, Context context) {
        this.staffList = staffList;
        this.context = context;
        this.items = new ArrayList<>(); // Initialize items
        setSortedList(staffList, isAscending); // Initial sort and grouping
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setSortedList(List<Staff> staffList, boolean ascending) {
        isAscending = ascending;
        items.clear();

        Collator collator = Collator.getInstance(new Locale("vi", "VN"));
        collator.setStrength(Collator.PRIMARY);

        // Create a copy for sorting
        List<Staff> sortedList = new ArrayList<>(staffList);

        Collections.sort(sortedList, (s1, s2) -> {
            // Handle null/empty names:
            String name1 = (s1.getName() == null || s1.getName().isEmpty()) ? "" : s1.getName();
            String name2 = (s2.getName() == null || s2.getName().isEmpty()) ? "" : s2.getName();

            if (isAscending) {
                return collator.compare(name1, name2);
            } else {
                return collator.compare(name2, name1);
            }
        });

        Map<String, List<Staff>> groupedStaff = new LinkedHashMap<>();
        for (Staff staff : sortedList) { //Iterate over sorted list
            String firstLetter = (staff.getName() != null && !staff.getName().isEmpty())
                    ? staff.getName().substring(0, 1).toUpperCase()
                    : "#";
            if (!groupedStaff.containsKey(firstLetter)) {
                groupedStaff.put(firstLetter, new ArrayList<>());
            }
            groupedStaff.get(firstLetter).add(staff);
        }

        for (Map.Entry<String, List<Staff>> entry : groupedStaff.entrySet()) {
            items.add(entry.getKey()); // Header
            items.addAll(entry.getValue()); // Staff items
        }

        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return (items.get(position) instanceof String) ? TYPE_HEADER : TYPE_ITEM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_staff_header, parent, false);
            return new HeaderViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_staff, parent, false);
            return new StaffViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {
            ((HeaderViewHolder) holder).bind((String) items.get(position));
        } else if (holder instanceof StaffViewHolder) {
            Staff staff = (Staff) items.get(position);
            ((StaffViewHolder) holder).bind(staff);
            ((StaffViewHolder) holder).cardView.setOnClickListener(v -> {
                Intent intent = new Intent(context, StaffDetailActivity.class);
                intent.putExtra("NAME", staff.getName());
                intent.putExtra("POSITION", staff.getPosition());
                intent.putExtra("DEPARTMENT", staff.getDepartment());
                intent.putExtra("PHONE", staff.getPhone());
                intent.putExtra("EMAIL", staff.getEmail());
                intent.putExtra("IMAGE", staff.getImageResource());
                context.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void filter(String text) {
        List<Staff> filteredStaff = new ArrayList<>();
        if (text.isEmpty()) {
            filteredStaff.addAll(staffList); // Use the original list
        } else {
            text = text.toLowerCase();
            for (Staff staff : staffList) {
                if (staff.getName().toLowerCase().contains(text) ||
                        staff.getPosition().toLowerCase().contains(text) ||
                        staff.getDepartment().toLowerCase().contains(text) ||
                        staff.getPhone().contains(text) ||
                        staff.getEmail().toLowerCase().contains(text)) {
                    filteredStaff.add(staff);
                }
            }
        }
        setSortedList(filteredStaff, isAscending);  // Apply grouping to filtered list
    }

    static class HeaderViewHolder extends RecyclerView.ViewHolder {
        private TextView headerTextView;

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            headerTextView = itemView.findViewById(R.id.header_text);
        }

        public void bind(String header) {
            headerTextView.setText(header);
        }
    }

    class StaffViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView nameTextView;
        private TextView positionTextView;
        private TextView departmentTextView;
        private CardView cardView;

        public StaffViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            positionTextView = itemView.findViewById(R.id.positionTextView);
            departmentTextView = itemView.findViewById(R.id.departmentTextView);
            cardView = itemView.findViewById(R.id.cardView);
        }

        public void bind(Staff staff) {
            nameTextView.setText(staff.getName());
            positionTextView.setText(staff.getPosition());
            departmentTextView.setText(staff.getDepartment());
            imageView.setImageResource(staff.getImageResource());
        }
    }
}
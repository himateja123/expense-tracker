package com.example.expensetracker.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.expensetracker.dto.ExpenseRequestDTO;
import com.example.expensetracker.dto.ExpenseResponseDTO;
import com.example.expensetracker.entity.Expense;
import com.example.expensetracker.service.ExpenseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private ExpenseService service;

    public ExpenseController(ExpenseService service) {
        this.service = service;
    }

    // ADD expense
    @PostMapping
    public ResponseEntity<ExpenseResponseDTO>addExpense(@Valid
            @RequestBody ExpenseRequestDTO dto) {
        ExpenseResponseDTO response = service.addExpense(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // GET all expenses
    @GetMapping
    public ResponseEntity<List<ExpenseResponseDTO>> getAllExpenses() {
    	List<ExpenseResponseDTO> list =service.getAllExpenses();
        return ResponseEntity.ok(list);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ExpenseResponseDTO> updateExpense(@PathVariable Long id,@RequestBody ExpenseRequestDTO dto) {
    	ExpenseResponseDTO update=service.updateExpense(id,dto);
    	return ResponseEntity.ok(update);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponseDTO> getbyid(@PathVariable Long id) {
    	
    	ExpenseResponseDTO get=service.getExpenseByid(id);
    	return ResponseEntity.ok(get);
    	
    	//System.out.println("id not found");
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ExpenseResponseDTO> deletebyid(@PathVariable Long id) {
    	
    	ExpenseResponseDTO delete=service.deleteExpenseById(id);
    	return ResponseEntity.ok(delete);
    }
}

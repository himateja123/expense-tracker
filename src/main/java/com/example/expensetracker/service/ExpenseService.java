package com.example.expensetracker.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.expensetracker.dto.ExpenseRequestDTO;
import com.example.expensetracker.dto.ExpenseResponseDTO;
import com.example.expensetracker.entity.Expense;
import com.example.expensetracker.globalExceptionHandler.ExpenseNotFoundException;
import com.example.expensetracker.repository.ExpenseRepository;

@Service
public class ExpenseService {

    private ExpenseRepository expenserepo;

    public ExpenseService(ExpenseRepository expenserepo) {
        this.expenserepo = expenserepo;
    }

    // CREATE
    public ExpenseResponseDTO addExpense(ExpenseRequestDTO dto) {

        Expense expense = new Expense();
        expense.setTitle(dto.getTitle());
        expense.setDate(dto.getDate());
        expense.setAmount(dto.getAmount());
        expense.setCategory(dto.getCategory());

        Expense save = expenserepo.save(expense);

        return new ExpenseResponseDTO(
                save.getId(),
                save.getTitle(),
                save.getDate(),
                save.getAmount(),
                save.getCategory()
        );
    }

    // READ ALL
    public List<ExpenseResponseDTO> getAllExpenses() {

        List<Expense> expenses = expenserepo.findAll();
        List<ExpenseResponseDTO> response = new ArrayList<>();

        for (Expense e : expenses) {
            response.add(
                new ExpenseResponseDTO(
                    e.getId(),
                    e.getTitle(),
                    e.getDate(),
                    e.getAmount(),
                    e.getCategory()
                )
            );
        }

        return response;
    }
    
    
    // UPDATE BY ID
    public ExpenseResponseDTO updateExpense(Long id ,ExpenseRequestDTO dto) {
    	Expense expense=expenserepo.findById(id).orElse(null);
    	
        if (expense == null) {
        	throw new ExpenseNotFoundException("Expense not found with the given id " + id);
        }
        expense.setTitle(dto.getTitle());
        expense.setDate(dto.getDate());
        expense.setAmount(dto.getAmount());
        expense.setCategory(dto.getCategory());

        Expense updated = expenserepo.save(expense);
    	
        return new ExpenseResponseDTO(
        		updated.getId(),
        		updated.getTitle(),
        		updated.getDate(),
        		updated.getAmount(),
        		updated.getCategory()
        );
    	
    }
    
    //GET BY ID 
    public ExpenseResponseDTO getExpenseByid(Long id) {
    	Expense expense=expenserepo.findById(id).orElse(null);
    	if(expense==null)
    		 throw new ExpenseNotFoundException("Expense not found with the given id " + id);
    	return new ExpenseResponseDTO(expense.getId(), expense.getTitle(),
    			expense.getDate(),
    			expense.getAmount(), expense.getCategory());
    	
    }
    
    // DELETE BY ID 
    
    public ExpenseResponseDTO deleteExpenseById(Long id) {
    	Expense expense=expenserepo.findById(id).orElse(null);
    	if(expense==null){
    		throw new ExpenseNotFoundException("Expense not found with the given id " + id+" It is already deleted...");
    	}
    	expenserepo.deleteById(id);
    	
    	return new ExpenseResponseDTO(expense.getId(), expense.getTitle(),
    			expense.getDate(),
    			expense.getAmount(), expense.getCategory());
    	
    }
}

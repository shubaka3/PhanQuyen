package com.hutech.demo.service;
import com.hutech.demo.model.Role;
import com.hutech.demo.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
/**
 * Service class for managing categories.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class RoleService {
    private final RoleRepository roleRepository;
    /**
     * Retrieve all categories from the database.
     * @return a list of categories
     */
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
    /**
     * Retrieve a category by its id.
     * @param id the id of the category to retrieve
     * @return an Optional containing the found category or empty if not found
     */
    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }
    /**
     * Add a new category to the database.
     * @param role the category to add
     */
    public void addRole(Role role) {
        roleRepository.save(role);
    }
    /**
     * Update an existing category.
     * @param role the category with updated information
     */
    public void updateRole(@NotNull Role role) {
        Role existingCategory = roleRepository.findById(role.getRole_id())
                .orElseThrow(() -> new IllegalStateException("Category with ID " +
                        role.getRole_id() + " does not exist."));
        existingCategory.setRole_name(role.getRole_name());
        roleRepository.save(existingCategory);
    }
/**
 * Delete a category by its id.
 * @param id the id of the category to delete
 */
    public void deleteRoleById(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new IllegalStateException("Category with ID " + id + " does not exist.");
        }
        roleRepository.deleteById(id);
    }
}

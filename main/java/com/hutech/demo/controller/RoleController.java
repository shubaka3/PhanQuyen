package com.hutech.demo.controller;
import com.hutech.demo.model.Role;
import com.hutech.demo.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;
@Controller
@RequiredArgsConstructor
public class RoleController {
    @Autowired
    private final RoleService roleService;
    @GetMapping("/roles/add")
    public String showAddForm(Model model) {
        model.addAttribute("role", new Role());
        return "/roles/add-role";
    }
    @PostMapping("/roles/add")
    public String addRole(@Valid Role role, BindingResult result) {
        if (result.hasErrors()) {
            return "/roles/add-role";
        }
        roleService.addRole(role);
        return "redirect:/roles";
    }
    // Hiển thị danh sách danh mục
    @GetMapping("/roles")
    public String listRoles(Model model) {
        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("roles", roles);
        return "/roles/roles-list";
    }
    // GET request to show category edit form
    @GetMapping("/roles/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Role role = roleService.getRoleById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid role Id:"
                        + id));
        model.addAttribute("role", role);
        return "/roles/update-role";
    }
    // POST request to update category
    @PostMapping("/roles/update/{id}")
    public String updateRole(@PathVariable("id") Long id, @Valid Role role,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            role.setRole_id(id);
            return "/roles/update-role";
        }
        roleService.updateRole(role);
        model.addAttribute("roles", roleService.getAllRoles());
        return "redirect:/roles";
    }
    // GET request for deleting category
    @GetMapping("/roles/delete/{id}")
    public String deleteRole(@PathVariable("id") Long id, Model model) {
        Role role = roleService.getRoleById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid role Id:"
                        + id));
        roleService.deleteRoleById(id);
        model.addAttribute("roles", roleService.getAllRoles());
        return "redirect:/roles";
    }
}

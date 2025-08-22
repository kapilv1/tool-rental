
package com.example.toolrentalapi.controller;

import com.example.toolrentalapi.model.Tool;
import com.example.toolrentalapi.model.ToolType;
import com.example.toolrentalapi.repo.ToolCatalog;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/tools")
public class ToolController {
    private final ToolCatalog catalog;
    public ToolController(ToolCatalog catalog) { this.catalog = catalog; }

    @GetMapping
    public Collection<Tool> list() { return catalog.findAll(); }

    @GetMapping("/{code}")
    public Tool get(@PathVariable("code") String code) {
        return catalog.getByCode(code);
    }

    @PostMapping
    public Tool add(@RequestParam String code, @RequestParam ToolType type, @RequestParam String brand) {
        return catalog.save(new Tool(code, type, brand));
    }

    @PutMapping("/{code}")
    public Tool update(@PathVariable String code, @RequestParam ToolType type, @RequestParam String brand) {
        Tool t = new Tool(code, type, brand);
        return catalog.save(t);
    }

    @DeleteMapping("/{code}")
    public void delete(@PathVariable String code) {
        catalog.delete(code);
    }
}

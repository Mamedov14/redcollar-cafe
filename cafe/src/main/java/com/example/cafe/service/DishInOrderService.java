package com.example.cafe.service;

import com.example.cafe.entity.DishInOrder;
import com.example.cafe.entity.Menu;
import com.example.cafe.repository.DishInOrderRepository;
import com.example.cafe.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DishInOrderService {

    private final DishInOrderRepository dishInOrderRepository;

    private final MenuRepository menuRepository;

    public List<DishInOrder> findAllDishInOrder() {
        return dishInOrderRepository.findAll();
    }

    public List<Menu> findAllMenus() {
        return menuRepository.findAll();
    }

    public List<DishInOrder> findAllActualDishInOrder(String date) {
        return dishInOrderRepository.findDishInOrderByDate(date);
    }

    public List<DishInOrder> dishInOrderList(Long id) {
        Optional<DishInOrder> dishInOrder = dishInOrderRepository.findById(id);
        ArrayList<DishInOrder> res = new ArrayList<>();
        dishInOrder.ifPresent(res::add);
        return res;
    }

    public DishInOrder save(String nameDish, String dateMenu, String address, String phoneNumber, String date, int count) {
        Menu menu = menuRepository.findMenuByDishNameAndDate(nameDish, dateMenu);
        return dishInOrderRepository.save(new DishInOrder(menu, address, phoneNumber, date, count));
    }

    public DishInOrder update(Long id, String nameDish, String dateMenu, String address, String phoneNumber, String date, int count) {
        DishInOrder dishInOrder = dishInOrderRepository.findById(id).orElseThrow();
        Menu menu = menuRepository.findMenuByDishNameAndDate(nameDish, dateMenu);
        dishInOrder.setMenu(menu);
        dishInOrder.setAddress(address);
        dishInOrder.setPhoneNumber(phoneNumber);
        dishInOrder.setDate(date);
        dishInOrder.setSum(count);
        return dishInOrderRepository.save(dishInOrder);
    }

    public void delete(Long id) {
        dishInOrderRepository.deleteById(id);
    }
}

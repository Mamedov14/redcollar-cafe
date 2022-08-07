package com.example.cafe.service;

import com.example.cafe.entity.Coworker;
import com.example.cafe.entity.Menu;
import com.example.cafe.entity.Order;
import com.example.cafe.repository.CoworkerRepository;
import com.example.cafe.repository.MenuRepository;
import com.example.cafe.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final CoworkerRepository coworkerRepository;

    private final MenuRepository menuRepository;


    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    public List<Coworker> findAllCoworkers() {
        return coworkerRepository.findAll();
    }

    public List<Menu> findAllMenus() {
        return menuRepository.findAll();
    }

    public List<Order> findAllActualOrder(String date) {
        return orderRepository.findOrderByDate(date);
    }

    public Order save(String nameCoworker, String nameDish, String dateMenu, String date, String time, int numberTable, int count) {
        Coworker coworker = coworkerRepository.findByFio(nameCoworker);
        Menu menu = menuRepository.findMenuByDishNameAndDate(nameDish, dateMenu);
        return orderRepository.save(new Order(coworker, menu, date, time, numberTable, count));
    }

    public List<Order> orderList(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        ArrayList<Order> res = new ArrayList<>();
        order.ifPresent(res::add);
        return res;
    }

    public Order update(Long id, String nameCoworker, String nameDish, String dateMenu, String date, String time, int numberTable, int count) {
        Order order = orderRepository.findById(id).orElseThrow();
        Coworker coworker = coworkerRepository.findByFio(nameCoworker);
        Menu menu = menuRepository.findMenuByDishNameAndDate(nameDish, dateMenu);
        order.setCoworker(coworker);
        order.setMenu(menu);
        order.setDate(date);
        order.setTime(time);
        order.setTableNumber(numberTable);
        order.setSum(count);
        return orderRepository.save(order);
    }

    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}

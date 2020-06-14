package api.controller;

import api.model.CatalogItem;
import api.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import api.service.CatalogItemService;
import api.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/")
public class CatalogItemController {

    @Autowired
    private CatalogItemService catalogItemService;
    @Autowired
    private OrderService orderService;

    @RequestMapping("get_all_Items")
    public ResponseEntity<List<CatalogItem>> getAllItems() {
        List<CatalogItem> itemList = catalogItemService.getAllItems();
        return new ResponseEntity<List<CatalogItem>>(itemList, HttpStatus.OK);
    }

    @RequestMapping("get_order_details")
    public ResponseEntity<Order> getOrder(@RequestParam("orderId") int orderId) {
        Order order = orderService.getOrder(orderId);
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }


    //note: Rest api.exception handling can be handled via @Controller advice ,@RestHandler in other package
}

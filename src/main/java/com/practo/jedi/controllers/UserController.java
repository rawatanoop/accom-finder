package com.practo.jedi.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.practo.jedi.dao.UserDao;
import com.practo.jedi.entity.User;

@RestController
@RequestMapping("/users")
public class UserController {
  @Autowired
  private UserDao userDao;

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<Iterable<User>> get() {
    Iterable<User> dto = userDao.findAll();
    ResponseEntity<Iterable<User>> re = new ResponseEntity<Iterable<User>>(dto, HttpStatus.OK);
    return re;
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<User> getUser(@PathVariable int id) {
    User userobj = userDao.findOne(id);
    ResponseEntity<User> re = new ResponseEntity<User>(userobj, HttpStatus.OK);
    return re;
  }

  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<User> create(@RequestBody User obj) {
    User userobj = userDao.save(obj);
    ResponseEntity<User> re = new ResponseEntity<User>(userobj, HttpStatus.CREATED);
    return re;
  }

  @RequestMapping(method = RequestMethod.PUT)
  public ResponseEntity<User> update(@RequestBody User obj) {
    User userobj = userDao.save(obj);
    ResponseEntity<User> re = new ResponseEntity<User>(userobj, HttpStatus.OK);
    return re;
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Boolean> delete(@PathVariable("id") int id, HttpServletResponse response) {
    userDao.delete(id);
    ResponseEntity<Boolean> re = new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
    return re;
  }

}

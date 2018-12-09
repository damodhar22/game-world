package com.game.home.console;

import com.game.home.world.entities.character.Role;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MenuWidgetTest {

   @Mock(name = "reader")
   private ConsoleScanner consoleScanner;

   @InjectMocks
   MenuWidget<Role> menuWidget = new MenuWidget<Role>("Roles menu",Role.values());

   @Test
   public void testMenuWidget(){
      MenuWidget<Role> menuWidget = new MenuWidget<Role>("Roles menu",Role.values());
      Assert.assertNotNull(menuWidget);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testMenuWidgetException(){
      MenuWidget<Dummy> menuWidget = new MenuWidget<Dummy>("Dummy menu",Dummy.values());
   }

   enum Dummy{
   }
}

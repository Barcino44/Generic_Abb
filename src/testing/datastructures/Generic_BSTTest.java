package datastructures;


import static org.junit.jupiter.api.Assertions.*;
import model.Cat;
import model.CatRace;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Generic_BSTTest {

    private IBinarySearchTree<String, Cat> bst;

    @BeforeEach
    void setUp() {
        // TODO - instantiate with own implementation.
        bst = new BinarySearchTree<>();
    }

    @Test
    void testInsertOneElementVerifyIsRoot() {
        // Arrange
        Cat firstCat = new Cat("Michi", CatRace.persian, 2);

        // Act
        bst.insert(firstCat.getName(), firstCat);

        // Assert
        assertEquals(firstCat, bst.getRoot());
    }

    @Test
    void testInsertTwoElementsVerifyOrder() {
        // Arrange
        Cat firstCat = new Cat("Michi", CatRace.persian, 2);
        Cat secondCat = new Cat("Tyr", CatRace.ragdoll, 1);

        // Act
        bst.insert(firstCat.getName(), firstCat);
        bst.insert(secondCat.getName(), secondCat);

        // Assert
        assertEquals("Michi Tyr", bst.inOrder());
    }

    @Test
    void testInsertThreeElementsVerifyOrder() {
        // Arrange
        Cat firstCat = new Cat("Michi", CatRace.persian, 2);
        Cat secondCat = new Cat("Tyr", CatRace.ragdoll, 1);
        Cat thirdCat = new Cat("Felix", CatRace.siamese, 3);

        // Act
        bst.insert(firstCat.getName(), firstCat);
        bst.insert(secondCat.getName(), secondCat);
        bst.insert(thirdCat.getName(), thirdCat);

        // Assert
        assertEquals("Felix Michi Tyr", bst.inOrder());
    }

    @Test
    void testSearchElementNotFound() {
        // Arrange
        Cat firstCat = new Cat("Michi", CatRace.persian, 2);

        // Act
        Cat result = bst.search(firstCat.getName());

        // Assert
        assertNull(result);
    }

    @Test
    void testSearchElementFoundRoot() {
        // Arrange
        Cat firstCat = new Cat("Michi", CatRace.persian, 2);
        bst.insert(firstCat.getName(), firstCat);

        // Act
        Cat result = bst.search(firstCat.getName());

        // Assert
        assertEquals(firstCat, result);
    }

    @Test
    void testSearchElementFoundNotRoot() {
        // Arrange
        Cat firstCat = new Cat("Michi", CatRace.persian, 2);
        Cat secondCat = new Cat("Tyr", CatRace.ragdoll, 1);
        Cat thirdCat = new Cat("Yoda", CatRace.siamese, 3);
        bst.insert(firstCat.getName(), firstCat);
        bst.insert(secondCat.getName(), secondCat);
        bst.insert(thirdCat.getName(), thirdCat);

        // Act
        Cat result = bst.search(thirdCat.getName());

        // Assert
        assertEquals(thirdCat, result);
    }

    @Test
    void testDeleteElementNotFound() {
        // Arrange
        Cat firstCat = new Cat("Michi", CatRace.persian, 2);

        // Act
        Cat result = bst.delete(firstCat.getName());

        // Assert
        assertNull(result);
    }

    @Test
    void testDeleteElementFoundRoot() {
        // Arrange
        Cat firstCat = new Cat("Michi", CatRace.persian, 2);
        bst.insert(firstCat.getName(), firstCat);

        // Act
        Cat result = bst.delete(firstCat.getName());

        // Assert
        assertEquals(firstCat, result);
        assertNull(bst.getRoot());
    }

    @Test
    void testDeleteElementFoundNotRootOneChild() {
        // Arrange
        Cat firstCat = new Cat("Michi", CatRace.persian, 2);
        Cat secondCat = new Cat("Tyr", CatRace.ragdoll, 1);
        bst.insert(firstCat.getName(), firstCat);
        bst.insert(secondCat.getName(), secondCat);

        // Act
        Cat result = bst.delete(firstCat.getName());

        // Assert
        assertEquals(firstCat, result);
        assertEquals(secondCat, bst.getRoot());
    }

    @Test
    void testDeleteElementFoundNotRootBothChildsOnly() {
        // Arrange
        Cat firstCat = new Cat("Michi", CatRace.persian, 2);
        Cat secondCat = new Cat("Tyr", CatRace.ragdoll, 1);
        Cat thirdCat = new Cat("Felix", CatRace.siamese, 3);
        bst.insert(firstCat.getName(), firstCat);
        bst.insert(secondCat.getName(), secondCat);
        bst.insert(thirdCat.getName(), thirdCat);

        // Act
        Cat result = bst.delete(firstCat.getName());

        // Assert
        assertEquals(firstCat, result);
        assertEquals(secondCat, bst.getRoot());
    }

    @Test
    void testDeleteElementFoundNotRootBothChildsMoreElements() {
        // Arrange
        Cat firstCat = new Cat("Michi", CatRace.persian, 2);
        Cat secondCat = new Cat("Tyr", CatRace.ragdoll, 1);
        Cat thirdCat = new Cat("Felix", CatRace.siamese, 3);
        Cat FourthCat = new Cat("Yoda", CatRace.siamese, 3);
        bst.insert(firstCat.getName(), firstCat);
        bst.insert(secondCat.getName(), secondCat);
        bst.insert(thirdCat.getName(), thirdCat);
        bst.insert(FourthCat.getName(), FourthCat);

        // Act
        Cat result = bst.delete(firstCat.getName());

        // Assert
        assertEquals(firstCat, result);
        assertEquals(secondCat, bst.getRoot());
        assertEquals("Felix Tyr Yoda", bst.inOrder());
    }

    @Test
    void testInOrderEmptyTreeReturnsEmptyString() {
        // Arrange

        // Act
        String inOrder= bst.inOrder();

        // Assert
        assertTrue(inOrder.isEmpty());
    }

    @Test
    void testInOrderMultipleElements() {
        // Arrange
        Cat firstCat = new Cat("Michi", CatRace.persian, 2);
        Cat secondCat = new Cat("Tyr", CatRace.ragdoll, 1);
        Cat thirdCat = new Cat("Felix", CatRace.siamese, 3);
        Cat FourthCat = new Cat("Yoda", CatRace.siamese, 3);
        bst.insert(firstCat.getName(), firstCat);
        bst.insert(secondCat.getName(), secondCat);
        bst.insert(thirdCat.getName(), thirdCat);
        bst.insert(FourthCat.getName(), FourthCat);

        // Act
        String inOrder = bst.inOrder();

        // Assert
        assertEquals("Felix Michi Tyr Yoda", inOrder);
    }
}
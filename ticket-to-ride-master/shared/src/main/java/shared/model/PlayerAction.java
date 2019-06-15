package shared.model;

import java.io.Serializable;

/**
 * Created by BenNelson on 2/3/18.
 *
 * A player on his or her turn can perform up to 3 actions.
 *
 * They are as follows:
 *
 * 1 - Draw 2 train _cards
 *     -to draw train _cards, a player may draw from: the face up pile, the traincard pile or one of each
 *     -the locomotive _cards count as 2 train _cards, unless the locomotive card is drawn from the traincard pile
 *
 * 2 - Claim a route
 * 3 - Draw Destination _cards
 *
 */
public class PlayerAction implements Serializable {
}

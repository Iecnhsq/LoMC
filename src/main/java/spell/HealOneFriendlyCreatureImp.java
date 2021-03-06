package spell;

import battle.Battle;
import ourlists.OnTableList;

public class HealOneFriendlyCreatureImp implements HealOneFriendlyCreature {

    private static Integer id = null;

    @Override
    public void doSpell(int amount, Battle batt) {
        OnTableList p2OnTable = batt.p2OnTable;
        OnTableList p1OnTable = batt.p1OnTable;
        if (!batt.p1MadeTurn && !batt.p2MadeTurn) {
            addHeals(p1OnTable, amount);
        } else if (batt.p1MadeTurn && !batt.p2MadeTurn) {
            addHeals(p2OnTable, amount);
        }
    }

    public static void setId(int idFriendlyCard) {
        id = idFriendlyCard;
    }

    private void addHeals(OnTableList OnTable, int amount) {
        OnTable.stream().filter((c) -> (c.getId() == id)).forEachOrdered((c) -> {
            c.setHealth(c.getHealth() + amount);
        });
    }

}

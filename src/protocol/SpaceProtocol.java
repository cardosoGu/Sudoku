package protocol;

public abstract class SpaceProtocol {

    private Integer actual;
    private int expected;
    private boolean fixed;

    public SpaceProtocol(int expected, boolean fixed) {
        this.expected = expected;
        this.fixed = fixed;
        if(fixed){
        actual = expected;
        }

    }

    public void cleanActual(){
        if(fixed) return;
        actual = null;
    }

    public Integer getActual() {
        return actual;
    }

    public void setActual(Integer actual) {
        if(fixed) return;

        this.actual = actual;
    }

    public int getExpected() {
        return expected;
    }

    public boolean isFixed() {
        return fixed;
    }
}

package algo.base;

public enum TestEnum {
    SETNEX;
    private final byte[] bytes;

    TestEnum() {
//        bytes = this.name().getBytes();
        bytes = new byte[0];
    }

    public static void main(String[] args) {
        System.out.println(TestEnum.SETNEX.bytes);
    }
}



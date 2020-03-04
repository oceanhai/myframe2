package com.example.java_lib.designpattern.builderpattern;

/**
 * 参考 https://www.cnblogs.com/LcMiho/p/10209279.html
 * 参考 寻医问药 网络请求 DataRequestWrapper
 */
public class BuildMan {
    private String head;
    private String body;
    private String foot;

    /**
     * builder.head 为什么可以直接引用啊？private的属性不应该只能通过get取得吗？ TODO 我擦
     * 参考 https://blog.csdn.net/qq_40940540/article/details/86739869
     *
     * 小结
     * 这部分private看上去失效可，实际上并没有失效，因为当内部类调用外部类的私有属性时，
     * 其真正的执行是调用了编译器生成的属性的静态方法（即acess$0,access$1等）来获取这些属性值。这一切都是编译器的特殊处理。
     *
     * 再来个总结
     * 其中java官方文档 有这样一句话
     * if the member or constructor is declared private, then access is permitted if and only if it occurs within the body of the top level class (§7.6) that encloses the declaration of the member or constructor.
     * 意思是 如果（内部类的）成员和构造方法设定成了私有修饰符，当且仅当其外部类访问时是允许的。
     *
     *
     * @param builder
     */
    public BuildMan(Builder builder) {
        this.head = builder.head;
        this.body = builder.body;
        this.foot = builder.foot;
    }

    public static class Builder{
        private String head;
        private String body;
        private String foot;

        public Builder() {
        }

        public BuildMan build(){
            return new BuildMan(this);
        }

        public Builder buildHead(String head){
            this.head = head;
            return this;
        }

        public Builder buildBody(String body){
            this.body = body;
            return this;
        }

        public Builder buildFoot(String foot){
            this.foot = foot;
            return this;
        }
    }

    @Override
    public String toString() {
        return "BuildMan{" +
                "head='" + head + '\'' +
                ", body='" + body + '\'' +
                ", foot='" + foot + '\'' +
                '}';
    }
}

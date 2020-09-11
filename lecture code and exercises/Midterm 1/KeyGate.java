public class KeyGate {
    public class FingerPrint {

    }
    public class Key {

    }
    public class SkeletonKey extends Key {

    }
    public class StandardBox {
        public void unlock(Key k) {
        }
    } // UK

    public class BioBox extends StandardBox {

        public void unlock(SkeletonKey sk) {
        } // USK

        public void unlock(FingerPrint f) {
        } // UF
    }
    public  void doStuff(Key k, SkeletonKey sk, FingerPrint f) {
        StandardBox sb = new StandardBox();
        StandardBox sbbb = new BioBox();
        BioBox bb = new BioBox();

        sb.unlock(k); // UK
        sbbb.unlock(k); // UK
        bb.unlock(k); // UK
        sb.unlock(sk); // Compile Error // WRONG!!! it's UK. sk extends k!
        sbbb.unlock(sk); // Compile Error // WRONG!!! it's UK. sk extends k!
        bb.unlock(sk); // USK
        //sb.unlock(f); // Compile Error
        //sbbb.unlock(f); // Compile Error
        bb.unlock(f); // UF

        bb = (BioBox) sbbb; //
        ((StandardBox) bb).unlock(sk); // Compile Error // WRONG!!! it's UK. sk extends k!
        ((StandardBox) sbbb).unlock(sk); // Compile Error // WRONG!!! it's UK. sk extends k!
        ((BioBox) sb).unlock(sk); // Runtime Error
    }
}

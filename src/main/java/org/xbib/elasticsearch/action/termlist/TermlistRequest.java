
package org.xbib.elasticsearch.action.termlist;

import java.io.IOException;

import org.elasticsearch.action.support.broadcast.BroadcastOperationRequest;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;

public class TermlistRequest extends BroadcastOperationRequest<TermlistRequest> {

    private String field;

    private Integer size;

    private boolean withDocFreq;

    private boolean withTotalFreq;

    private Integer maxDFpct;

    private Integer minDF;

    TermlistRequest() {
    }

    public TermlistRequest(String... indices) {
        super(indices);
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getSize() {
        return size;
    }

    public void setWithDocFreq(boolean withDocFreq) {
        this.withDocFreq = withDocFreq;
    }

    public boolean getWithDocFreq() {
        return withDocFreq;
    }
    public void setWithTotalFreq(boolean withTotalFreq) {
        this.withTotalFreq = withTotalFreq;
    }

    public boolean getWithTotalFreq() {
        return withTotalFreq;
    }

    public void setMaxDFpct(Integer maxDFpct) {this.maxDFpct = maxDFpct;}
    public Integer getMaxDFpct() {return maxDFpct;}

    public void setMinDF(Integer minDF) {this.minDF = minDF;}
    public Integer getMinDF() {return minDF;}

    @Override
    public void readFrom(StreamInput in) throws IOException {
        super.readFrom(in);
        field = in.readString();
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        super.writeTo(out);
        out.writeString(field);
    }
}

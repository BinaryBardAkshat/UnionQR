package androidx.appcompat.widget;

import android.view.textclassifier.TextClassificationManager;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;
import androidx.core.app.DialogCompat$$ExternalSyntheticApiModelOutline0;
import androidx.core.util.Preconditions;

final class AppCompatTextClassifierHelper {
    private TextClassifier mTextClassifier;
    private TextView mTextView;

    AppCompatTextClassifierHelper(TextView textView) {
        this.mTextView = (TextView) Preconditions.checkNotNull(textView);
    }

    public void setTextClassifier(TextClassifier textClassifier) {
        this.mTextClassifier = textClassifier;
    }

    public TextClassifier getTextClassifier() {
        TextClassifier textClassifier = this.mTextClassifier;
        if (textClassifier != null) {
            return textClassifier;
        }
        TextClassificationManager m = DialogCompat$$ExternalSyntheticApiModelOutline0.m(this.mTextView.getContext().getSystemService(DialogCompat$$ExternalSyntheticApiModelOutline0.m()));
        if (m != null) {
            return m.getTextClassifier();
        }
        return DialogCompat$$ExternalSyntheticApiModelOutline0.m();
    }
}

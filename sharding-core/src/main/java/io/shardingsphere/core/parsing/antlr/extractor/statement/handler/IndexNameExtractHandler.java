/*
 * Copyright 2016-2018 shardingsphere.io.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package io.shardingsphere.core.parsing.antlr.extractor.statement.handler;

import org.antlr.v4.runtime.ParserRuleContext;

import com.google.common.base.Optional;

import io.shardingsphere.core.parsing.antlr.extractor.statement.handler.result.IndexExtractResult;
import io.shardingsphere.core.parsing.antlr.extractor.statement.util.ASTUtils;
import io.shardingsphere.core.parsing.parser.token.IndexToken;
import io.shardingsphere.core.util.SQLUtil;

/**
 * Index name extract handler.
 * 
 * @author duhongjun
 */
public final class IndexNameExtractHandler implements ASTExtractHandler<Optional<IndexExtractResult>> {
    
    @Override
    public Optional<IndexExtractResult> extract(final ParserRuleContext ancestorNode) {
        Optional<ParserRuleContext> indexNameNode = ASTUtils.findFirstChildNode(ancestorNode, RuleName.INDEX_NAME);
        if (!indexNameNode.isPresent()) {
            return Optional.absent();
        }
        String name = SQLUtil.getNameWithoutSchema(indexNameNode.get().getText());
        return Optional.of(new IndexExtractResult(name, new IndexToken(indexNameNode.get().getStop().getStartIndex(), name, null)));
    }
}

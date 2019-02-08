// Generated from /Users/pieter.bos/projects/openehr/archie/grammars/src/main/antlr/Adl14.g4 by ANTLR 4.7
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link Adl14Parser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface Adl14Visitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#adl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdl(Adl14Parser.AdlContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#archetype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArchetype(Adl14Parser.ArchetypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#specialization_section}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpecialization_section(Adl14Parser.Specialization_sectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#language_section}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLanguage_section(Adl14Parser.Language_sectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#description_section}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDescription_section(Adl14Parser.Description_sectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#definition_section}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefinition_section(Adl14Parser.Definition_sectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#rules_section}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRules_section(Adl14Parser.Rules_sectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#terminology_section}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerminology_section(Adl14Parser.Terminology_sectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#concept_section}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConcept_section(Adl14Parser.Concept_sectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#meta_data}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMeta_data(Adl14Parser.Meta_dataContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#meta_data_item}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMeta_data_item(Adl14Parser.Meta_data_itemContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#meta_data_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMeta_data_value(Adl14Parser.Meta_data_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#meta_data_tag_adl_version}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMeta_data_tag_adl_version(Adl14Parser.Meta_data_tag_adl_versionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#meta_data_tag_uid}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMeta_data_tag_uid(Adl14Parser.Meta_data_tag_uidContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#meta_data_tag_build_uid}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMeta_data_tag_build_uid(Adl14Parser.Meta_data_tag_build_uidContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#meta_data_tag_rm_release}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMeta_data_tag_rm_release(Adl14Parser.Meta_data_tag_rm_releaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#meta_data_tag_is_controlled}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMeta_data_tag_is_controlled(Adl14Parser.Meta_data_tag_is_controlledContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#meta_data_tag_is_generated}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMeta_data_tag_is_generated(Adl14Parser.Meta_data_tag_is_generatedContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#domainSpecificExtension}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainSpecificExtension(Adl14Parser.DomainSpecificExtensionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#c_complex_object}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitC_complex_object(Adl14Parser.C_complex_objectContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#c_objects}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitC_objects(Adl14Parser.C_objectsContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#sibling_order}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSibling_order(Adl14Parser.Sibling_orderContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#c_non_primitive_object_ordered}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitC_non_primitive_object_ordered(Adl14Parser.C_non_primitive_object_orderedContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#c_non_primitive_object}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitC_non_primitive_object(Adl14Parser.C_non_primitive_objectContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#c_archetype_root}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitC_archetype_root(Adl14Parser.C_archetype_rootContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#c_complex_object_proxy}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitC_complex_object_proxy(Adl14Parser.C_complex_object_proxyContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#archetype_slot}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArchetype_slot(Adl14Parser.Archetype_slotContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#c_archetype_slot_head}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitC_archetype_slot_head(Adl14Parser.C_archetype_slot_headContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#c_archetype_slot_id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitC_archetype_slot_id(Adl14Parser.C_archetype_slot_idContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#c_attribute_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitC_attribute_def(Adl14Parser.C_attribute_defContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#c_attribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitC_attribute(Adl14Parser.C_attributeContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#c_attribute_tuple}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitC_attribute_tuple(Adl14Parser.C_attribute_tupleContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#c_object_tuple}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitC_object_tuple(Adl14Parser.C_object_tupleContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#c_object_tuple_items}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitC_object_tuple_items(Adl14Parser.C_object_tuple_itemsContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#c_object_tuple_item}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitC_object_tuple_item(Adl14Parser.C_object_tuple_itemContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#c_includes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitC_includes(Adl14Parser.C_includesContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#c_excludes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitC_excludes(Adl14Parser.C_excludesContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#c_existence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitC_existence(Adl14Parser.C_existenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#existence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExistence(Adl14Parser.ExistenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#c_cardinality}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitC_cardinality(Adl14Parser.C_cardinalityContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#cardinality}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCardinality(Adl14Parser.CardinalityContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#ordering_mod}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrdering_mod(Adl14Parser.Ordering_modContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#unique_mod}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnique_mod(Adl14Parser.Unique_modContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#multiplicity_mod}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicity_mod(Adl14Parser.Multiplicity_modContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#c_occurrences}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitC_occurrences(Adl14Parser.C_occurrencesContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#multiplicity}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicity(Adl14Parser.MultiplicityContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#assertion_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssertion_list(Adl14Parser.Assertion_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssertion(Adl14Parser.AssertionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#variableDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclaration(Adl14Parser.VariableDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#booleanAssertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanAssertion(Adl14Parser.BooleanAssertionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(Adl14Parser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#booleanForAllExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanForAllExpression(Adl14Parser.BooleanForAllExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#booleanOrExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanOrExpression(Adl14Parser.BooleanOrExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#booleanAndExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanAndExpression(Adl14Parser.BooleanAndExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#booleanXorExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanXorExpression(Adl14Parser.BooleanXorExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#booleanNotExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanNotExpression(Adl14Parser.BooleanNotExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#booleanConstraintExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanConstraintExpression(Adl14Parser.BooleanConstraintExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#booleanConstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanConstraint(Adl14Parser.BooleanConstraintContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#equalityExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualityExpression(Adl14Parser.EqualityExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#relOpExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelOpExpression(Adl14Parser.RelOpExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#arithmeticExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmeticExpression(Adl14Parser.ArithmeticExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#expressionLeaf}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionLeaf(Adl14Parser.ExpressionLeafContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#argumentList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgumentList(Adl14Parser.ArgumentListContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#functionName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionName(Adl14Parser.FunctionNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#adlRulesPath}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdlRulesPath(Adl14Parser.AdlRulesPathContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#variableReference}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableReference(Adl14Parser.VariableReferenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#plusMinusBinop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlusMinusBinop(Adl14Parser.PlusMinusBinopContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#multBinop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultBinop(Adl14Parser.MultBinopContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#powBinop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPowBinop(Adl14Parser.PowBinopContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#equalityBinop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualityBinop(Adl14Parser.EqualityBinopContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#relationalBinop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationalBinop(Adl14Parser.RelationalBinopContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#booleanLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanLiteral(Adl14Parser.BooleanLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#c_primitive_object}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitC_primitive_object(Adl14Parser.C_primitive_objectContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#c_integer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitC_integer(Adl14Parser.C_integerContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#assumed_integer_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssumed_integer_value(Adl14Parser.Assumed_integer_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#c_real}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitC_real(Adl14Parser.C_realContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#assumed_real_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssumed_real_value(Adl14Parser.Assumed_real_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#c_date_time}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitC_date_time(Adl14Parser.C_date_timeContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#assumed_date_time_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssumed_date_time_value(Adl14Parser.Assumed_date_time_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#c_date}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitC_date(Adl14Parser.C_dateContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#assumed_date_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssumed_date_value(Adl14Parser.Assumed_date_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#c_time}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitC_time(Adl14Parser.C_timeContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#assumed_time_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssumed_time_value(Adl14Parser.Assumed_time_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#c_duration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitC_duration(Adl14Parser.C_durationContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#assumed_duration_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssumed_duration_value(Adl14Parser.Assumed_duration_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#c_string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitC_string(Adl14Parser.C_stringContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#assumed_string_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssumed_string_value(Adl14Parser.Assumed_string_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#c_terminology_code}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitC_terminology_code(Adl14Parser.C_terminology_codeContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#localTermCode}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocalTermCode(Adl14Parser.LocalTermCodeContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#qualifiedTermCode}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQualifiedTermCode(Adl14Parser.QualifiedTermCodeContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#c_boolean}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitC_boolean(Adl14Parser.C_booleanContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#assumed_boolean_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssumed_boolean_value(Adl14Parser.Assumed_boolean_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#c_ordinal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitC_ordinal(Adl14Parser.C_ordinalContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#ordinal_term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrdinal_term(Adl14Parser.Ordinal_termContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#adl_path}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdl_path(Adl14Parser.Adl_pathContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#string_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString_value(Adl14Parser.String_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#string_list_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString_list_value(Adl14Parser.String_list_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#integer_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInteger_value(Adl14Parser.Integer_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#integer_list_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInteger_list_value(Adl14Parser.Integer_list_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#integer_interval_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInteger_interval_value(Adl14Parser.Integer_interval_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#integer_interval_list_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInteger_interval_list_value(Adl14Parser.Integer_interval_list_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#real_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReal_value(Adl14Parser.Real_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#real_list_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReal_list_value(Adl14Parser.Real_list_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#real_interval_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReal_interval_value(Adl14Parser.Real_interval_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#real_interval_list_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReal_interval_list_value(Adl14Parser.Real_interval_list_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#boolean_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolean_value(Adl14Parser.Boolean_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#boolean_list_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolean_list_value(Adl14Parser.Boolean_list_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#character_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharacter_value(Adl14Parser.Character_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#character_list_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharacter_list_value(Adl14Parser.Character_list_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#date_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDate_value(Adl14Parser.Date_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#date_list_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDate_list_value(Adl14Parser.Date_list_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#date_interval_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDate_interval_value(Adl14Parser.Date_interval_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#date_interval_list_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDate_interval_list_value(Adl14Parser.Date_interval_list_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#time_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTime_value(Adl14Parser.Time_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#time_list_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTime_list_value(Adl14Parser.Time_list_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#time_interval_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTime_interval_value(Adl14Parser.Time_interval_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#time_interval_list_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTime_interval_list_value(Adl14Parser.Time_interval_list_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#date_time_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDate_time_value(Adl14Parser.Date_time_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#date_time_list_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDate_time_list_value(Adl14Parser.Date_time_list_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#date_time_interval_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDate_time_interval_value(Adl14Parser.Date_time_interval_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#date_time_interval_list_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDate_time_interval_list_value(Adl14Parser.Date_time_interval_list_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#duration_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDuration_value(Adl14Parser.Duration_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#duration_list_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDuration_list_value(Adl14Parser.Duration_list_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#duration_interval_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDuration_interval_value(Adl14Parser.Duration_interval_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#duration_interval_list_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDuration_interval_list_value(Adl14Parser.Duration_interval_list_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#term_code_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm_code_value(Adl14Parser.Term_code_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#term_code_list_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm_code_list_value(Adl14Parser.Term_code_list_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#uri_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUri_value(Adl14Parser.Uri_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#relop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelop(Adl14Parser.RelopContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#type_id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_id(Adl14Parser.Type_idContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#attribute_id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttribute_id(Adl14Parser.Attribute_idContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(Adl14Parser.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#archetype_ref}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArchetype_ref(Adl14Parser.Archetype_refContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#odin_text}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOdin_text(Adl14Parser.Odin_textContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#attr_vals}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttr_vals(Adl14Parser.Attr_valsContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#attr_val}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttr_val(Adl14Parser.Attr_valContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#object_block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObject_block(Adl14Parser.Object_blockContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#object_value_block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObject_value_block(Adl14Parser.Object_value_blockContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#keyed_object}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyed_object(Adl14Parser.Keyed_objectContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#primitive_object}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitive_object(Adl14Parser.Primitive_objectContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#primitive_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitive_value(Adl14Parser.Primitive_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#primitive_list_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitive_list_value(Adl14Parser.Primitive_list_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#primitive_interval_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitive_interval_value(Adl14Parser.Primitive_interval_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#object_reference_block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObject_reference_block(Adl14Parser.Object_reference_blockContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#odin_path_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOdin_path_list(Adl14Parser.Odin_path_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link Adl14Parser#odin_path}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOdin_path(Adl14Parser.Odin_pathContext ctx);
}